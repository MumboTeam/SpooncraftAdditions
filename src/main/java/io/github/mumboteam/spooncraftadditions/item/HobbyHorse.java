package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class HobbyHorse extends Item implements PolymerItem {
    public HobbyHorse(Item.Properties properties) {
        super(properties.rarity(Rarity.UNCOMMON).stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.STICK;
    }



    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (level instanceof ServerLevel serverLevel) {
            Vec3 lookAngle = player.getLookAngle();
            double lungeStrength = 3 * 0.458;

            player.setDeltaMovement(player.getDeltaMovement().add(
                    lookAngle.x * lungeStrength,
                    0,
                    lookAngle.z * lungeStrength
            ));
            player.hurtMarked = true;

            serverLevel.playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.HORSE_GALLOP,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            if (!player.isCreative() && !player.isSpectator()) {
                player.causeFoodExhaustion(12.0F);
            }

            stack.hurtAndBreak(1, player, player.getUsedItemHand());
        }

        player.getCooldowns().addCooldown(stack, 20);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions.hobby_horse.desc").withStyle(ChatFormatting.GRAY));
    }
}
