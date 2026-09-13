package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class PlaceableCosmetic extends PolymerBlockItem {
    private final String TOOLTIP;

    public PlaceableCosmetic(Block block, Properties settings, String tooltip) {
        this.TOOLTIP = tooltip;
        super(block, settings);
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable(this.TOOLTIP).withStyle(ChatFormatting.GRAY));
    }
}
