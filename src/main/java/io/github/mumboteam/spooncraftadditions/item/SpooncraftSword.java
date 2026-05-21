package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomModelData;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SpooncraftSword extends Item implements PolymerItem {
    public SpooncraftSword(Properties settings) {
        super(settings.sword(SpooncraftMaterial.MATERIAL, 3, -2.4F).fireResistant().rarity(Rarity.EPIC).component(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(0f), List.of(), List.of(), List.of())));
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(0, Component.translatable("item.spooncraftadditions.spooncraft_sword.desc[0]").withStyle(ChatFormatting.RED));
        tooltip.add(1, Component.translatable("item.spooncraftadditions.spooncraft_sword.desc[1]").withStyle(ChatFormatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.NETHERITE_SWORD;
    }

    @Override
    public void hurtEnemy(ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
        CustomModelData cmd = stack.get(DataComponents.CUSTOM_MODEL_DATA);
        if (cmd != null && cmd.getFloat(0) != null) {
            stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of((stack.get(DataComponents.CUSTOM_MODEL_DATA).getFloat(0) + 1) % 6) ,List.of(), List.of(), List.of()));
        } else {
            stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(1f), List.of(), List.of(), List.of()));
        }

        super.hurtEnemy(stack, target, attacker);
    }
}
