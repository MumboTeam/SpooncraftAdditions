package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;

import java.util.List;

public class Hat extends Item implements PolymerItem {
    private final String hat;

    public Hat(Properties settings, String hat, ArmorMaterial material) {
        super(settings.humanoidArmor(material, ArmorType.HELMET).stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).build()).overrideDescription("item.spooncraftadditions." + hat));
        this.hat = hat;
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions." + hat +".desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.PAPER;
    }
}
