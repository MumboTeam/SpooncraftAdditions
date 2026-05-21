package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SpooncraftSpoon extends ShovelItem implements PolymerItem {
    public SpooncraftSpoon(Properties settings) {
        super(SpooncraftMaterial.MATERIAL, 1.5F, -3.0F, settings.fireResistant().rarity(Rarity.EPIC));
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(0, Component.translatable("item.spooncraftadditions.spooncraft_spoon.desc[0]").withStyle(ChatFormatting.RED));
        tooltip.add(1, Component.translatable("item.spooncraftadditions.spooncraft_spoon.desc[1]").withStyle(ChatFormatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.NETHERITE_SHOVEL;
    }

    @Override
    public boolean canDestroyBlock(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level world, @NonNull BlockPos pos, LivingEntity user) {
        if (!isUsable(user.getMainHandItem())) {
            return false;
        }

        return super.canDestroyBlock(stack, state, world, pos, user);
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        if (isUsable(context.getItemInHand())) {
            return super.useOn(context);
        }

        return InteractionResult.FAIL;
    }
}
