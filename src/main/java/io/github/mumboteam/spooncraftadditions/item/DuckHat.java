package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.entity.DuckEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class DuckHat extends BoatItem implements PolymerItem {
    private final Identifier cmd;

    public DuckHat(Settings settings, ArmorMaterial material, EntityType<DuckEntity> entityType) {
        super(entityType, material.applySettings(settings, EquipmentType.HELMET).maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).build()).translationKey("item.spooncraftadditions.duck_hat"));
        this.cmd = PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/hat/duck_hat"));
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.duck_hat.desc").formatted(Formatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.PAPER;
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return this.cmd;
    }
}
