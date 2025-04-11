package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class SpooncraftSword extends Item implements PolymerItem {
    public SpooncraftSword(Settings settings) {
        super(settings.sword(SpooncraftMaterial.MATERIAL, 3, -2.4F).fireproof().rarity(Rarity.EPIC).component(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(0f), List.of(), List.of(), List.of())));
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(0, Text.translatable("item.spooncraftadditions.spooncraft_sword.desc[0]").formatted(Formatting.RED));
        tooltip.add(1, Text.translatable("item.spooncraftadditions.spooncraft_sword.desc[1]").formatted(Formatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.NETHERITE_SWORD;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        CustomModelDataComponent cmd = stack.get(DataComponentTypes.CUSTOM_MODEL_DATA);
        if (cmd != null && cmd.getFloat(0) != null) {
            stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of((stack.get(DataComponentTypes.CUSTOM_MODEL_DATA).getFloat(0) + 1) % 6) ,List.of(), List.of(), List.of()));
        } else {
            stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(1f), List.of(), List.of(), List.of()));
        }

        super.postHit(stack, target, attacker);
    }
}
