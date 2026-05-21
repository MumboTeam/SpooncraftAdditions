package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;

import java.util.List;

public class MusicDisc extends Item implements PolymerItem {
    private final String disc;

    public MusicDisc(Properties settings, String disc, ResourceKey<JukeboxSong> jukeboxSong) {
        super(settings.stacksTo(1).overrideDescription("item.minecraft.music_disc_cat").rarity(Rarity.UNCOMMON).jukeboxPlayable(jukeboxSong));
        this.disc = disc;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.MUSIC_DISC_CAT;
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(Component.translatable("item.spooncraftadditions.music_disc_" + this.disc + ".desc").withStyle(ChatFormatting.GRAY));
    }
}
