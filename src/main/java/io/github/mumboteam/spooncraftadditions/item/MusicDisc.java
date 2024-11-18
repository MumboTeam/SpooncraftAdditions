package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class MusicDisc extends Item implements PolymerItem {
    private final String disc;

    public MusicDisc(Settings settings, String disc, RegistryKey<JukeboxSong> jukeboxSong) {
        super(settings.maxCount(1).translationKey("item.minecraft.music_disc_cat").rarity(Rarity.UNCOMMON).jukeboxPlayable(jukeboxSong));
        this.disc = disc;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.MUSIC_DISC_CAT;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(Text.translatable("item.spooncraftadditions.music_disc_" + this.disc + ".desc").formatted(Formatting.GRAY));
    }
}
