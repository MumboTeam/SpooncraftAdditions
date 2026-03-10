package io.github.mumboteam.spooncraftadditions.entity;

import com.mojang.datafixers.util.Pair;
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.mixin.AbstractBoatAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class DuckEntity extends Boat implements PolymerEntity {
    ItemStack itemStack;
    int quackCooldown = 0;

    public DuckEntity(EntityType<? extends DuckEntity> entityType, Level world, Supplier<Item> itemSupplier) {
        super(entityType, world, itemSupplier);
        this.setInvisible(true);
        this.itemStack = Items.PAPER.getDefaultInstance();
        this.itemStack.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "duck"));
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext context) {
        return EntityType.ZOMBIE;
    }

    @Override
    public List<Pair<EquipmentSlot, ItemStack>> getPolymerVisibleEquipment(List<Pair<EquipmentSlot, ItemStack>> items, ServerPlayer player) {
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

        if (this.getControllingPassenger() instanceof ServerPlayer player) {
            ((AbstractBoatAccessor)this).callFloatBoat();
            this.move(MoverType.SELF, this.getDeltaMovement());

            if (player.getLastClientInput().forward()) {
                this.move(MoverType.PLAYER, Vec3.directionFromRotation(0, this.getYRot()).scale(0.3F));
                this.absSnapTo(this.getX(), this.getY(), this.getZ(), Mth.rotLerp(0.2F, this.getYRot(), player.getYRot()), this.getXRot());
            } else if (player.getLastClientInput().backward()) {
                this.move(MoverType.PLAYER, Vec3.directionFromRotation(0, this.getYRot()).reverse().scale(0.3F));
                this.absSnapTo(this.getX(), this.getY(), this.getZ(), Mth.rotLerp(0.2F, this.getYRot(), player.getYRot()), this.getXRot());
            }
            if (player.getLastClientInput().left()) {
                this.absSnapTo(this.getX(), this.getY(), this.getZ(), this.getYRot() - 5.0F, this.getXRot());
            }
            if (player.getLastClientInput().right()) {
                this.absSnapTo(this.getX(), this.getY(), this.getZ(), this.getYRot() + 5.0F, this.getXRot());
            }
            if (player.getLastClientInput().jump() && this.quackCooldown == 0) {
                this.playSound(SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "entity.duck.quack")), 1.0F, 1.0F);
                this.quackCooldown = 20;
            }

            if (this.quackCooldown > 0) {quackCooldown--;}
        }
    }


    @Override
    public float getYHeadRot() {
        return this.getYRot();
    }

    @Override
    public Vec3 trackingPosition() {
        return super.trackingPosition().subtract(0, 1.5, 0);
    }
}
