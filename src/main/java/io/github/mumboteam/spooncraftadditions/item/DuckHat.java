package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.entity.DuckEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;

import java.util.List;

public class DuckHat extends BoatItem implements PolymerItem {
    public DuckHat(Properties settings, ArmorMaterial material, EntityType<DuckEntity> entityType) {
        super(entityType, settings.humanoidArmor(material, ArmorType.HELMET).stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).build()).overrideDescription("item.spooncraftadditions.duck_hat"));
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions.duck_hat.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.OAK_BOAT;
    }
}
