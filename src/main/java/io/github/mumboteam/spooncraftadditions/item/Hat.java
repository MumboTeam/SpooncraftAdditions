package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hat extends ArmorItem implements PolymerItem, Equipment {
    private final PolymerModelData cmd;
    private final String hat;

    public Hat(Settings settings, String hat, RegistryEntry<ArmorMaterial> material) {
        super(material, Type.HELMET, settings.maxCount(1).component(DataComponentTypes.ITEM_NAME, Text.translatable("item.spooncraftadditions." + hat)));
        this.cmd = PolymerResourcePackUtils.requestModel(Items.CARVED_PUMPKIN, Identifier.of(SpooncraftAdditions.ID, "item/hat/" + hat));
        this.hat = hat;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, @Nullable ServerPlayerEntity player) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions." + hat +".desc").formatted(Formatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.CARVED_PUMPKIN;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.cmd.value();
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
