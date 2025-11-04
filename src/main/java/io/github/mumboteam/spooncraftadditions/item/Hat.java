package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class Hat extends Item implements PolymerItem {
    private final String hat;

    public Hat(Settings settings, String hat, ArmorMaterial material) {
        super(settings.armor(material, EquipmentType.HELMET).maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).build()).translationKey("item.spooncraftadditions." + hat));
        this.hat = hat;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions." + hat +".desc").formatted(Formatting.GRAY));
        if (stack.getOrDefault(ModComponents.DISABLED, false) == true) {
            tooltip.addLast(Text.translatable("item.spooncraftadditions.hat.disabled").formatted(Formatting.RED));
        }
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.PAPER;
    }
}
