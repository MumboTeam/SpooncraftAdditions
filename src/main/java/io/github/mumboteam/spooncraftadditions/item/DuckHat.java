package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.entity.DuckEntity;
import io.github.mumboteam.spooncraftadditions.registry.ModEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.Equippable;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class DuckHat extends Item implements PolymerItem {
    public DuckHat(Properties settings) {
        super(settings.stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).build()).overrideDescription("item.spooncraftadditions.duck_hat"));
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions.duck_hat.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.OAK_BOAT;
    }

    @Override
    public @NonNull InteractionResult use(final @NonNull Level level, final Player player, final @NonNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            DuckEntity duck = ModEntityTypes.DUCK.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            if (duck != null) {
                Vec3 location = hitResult.getLocation();
                duck.setInitialPos(location.x, location.y, location.z);
                if (level instanceof ServerLevel serverLevel) {
                    EntityType.<AbstractBoat>createDefaultStackConfig(serverLevel, itemStack, player).apply(duck);
                }
                duck.setHatItem(itemStack.copy());

                if (!level.isClientSide()) {
                    level.addFreshEntity(duck);
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, location);
                    itemStack.consume(1, player);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResult.SUCCESS.withoutItem();
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            return InteractionResult.FAIL;
        }

    }
}
