package io.github.mumboteam.spooncraftadditions.entity;

import com.mojang.datafixers.util.Pair;
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.mixin.AbstractBoatEntityAccessor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DuckEntity extends BoatEntity implements PolymerEntity {
    ItemStack itemStack;
    int quackCooldown = 0;

    public DuckEntity(EntityType<? extends DuckEntity> entityType, World world, Supplier<Item> itemSupplier) {
        super(entityType, world, itemSupplier);
        this.setInvisible(true);
        this.itemStack = Items.PAPER.getDefaultStack();
        this.itemStack.set(DataComponentTypes.ITEM_MODEL, Identifier.of(SpooncraftAdditions.ID, "duck"));
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.ZOMBIE;
    }

    @Override
    public List<Pair<EquipmentSlot, ItemStack>> getPolymerVisibleEquipment(List<Pair<EquipmentSlot, ItemStack>> items, ServerPlayerEntity player) {
        List<Pair<EquipmentSlot, ItemStack>> list = new ArrayList<>();
        list.add(new Pair<>(EquipmentSlot.HEAD, itemStack));

        return list;
    }

    @Override
    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getControllingPassenger() instanceof ServerPlayerEntity player) {
            ((AbstractBoatEntityAccessor)this).callUpdateVelocity();
            this.move(MovementType.SELF, this.getVelocity());

            if (player.getPlayerInput().forward()) {
                this.move(MovementType.PLAYER, Vec3d.fromPolar(0, this.getYaw()).multiply(0.3F));
                this.updatePositionAndAngles(this.getX(), this.getY(), this.getZ(), MathHelper.lerpAngleDegrees(0.2F, this.getYaw(), player.getYaw()), this.getPitch());
            } else if (player.getPlayerInput().backward()) {
                this.move(MovementType.PLAYER, Vec3d.fromPolar(0, this.getYaw()).negate().multiply(0.3F));
                this.updatePositionAndAngles(this.getX(), this.getY(), this.getZ(), MathHelper.lerpAngleDegrees(0.2F, this.getYaw(), player.getYaw()), this.getPitch());
            }
            if (player.getPlayerInput().left()) {
                this.updatePositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw() - 5.0F, this.getPitch());
            }
            if (player.getPlayerInput().right()) {
                this.updatePositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw() + 5.0F, this.getPitch());
            }
            if (player.getPlayerInput().jump()) {
                if (this.quackCooldown == 0) {
                    this.playSound(SoundEvent.of(Identifier.of(SpooncraftAdditions.ID, "entity.duck.quack")), 1.0F, 1.0F);
                    this.quackCooldown = 20;
                }
            }

            if (this.quackCooldown > 0) {quackCooldown--;}
        }
    }


    @Override
    public float getHeadYaw() {
        return this.getYaw();
    }

    @Override
    public Vec3d getSyncedPos() {
        return super.getSyncedPos().subtract(0, 1.5, 0);
    }
}
