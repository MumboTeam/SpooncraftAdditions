package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MusicDisc extends Item implements PolymerItem {
    private final PolymerModelData cmd;
    private final String disc;

    public MusicDisc(Settings settings, String disc, RegistryKey<JukeboxSong> jukeboxSong) {
        super(settings.maxCount(1).component(DataComponentTypes.ITEM_NAME, Text.translatable("item.minecraft.music_disc_cat")).rarity(Rarity.RARE).jukeboxPlayable(jukeboxSong));
        this.disc = disc;
        this.cmd = PolymerResourcePackUtils.requestModel(Items.MUSIC_DISC_CAT, Identifier.of(SpooncraftAdditions.ID, "item/music_disc_" + this.disc));
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, @Nullable ServerPlayerEntity player) {
        tooltip.add(Text.translatable("item.spooncraftadditions.music_disc_" + this.disc + ".desc").formatted(Formatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.MUSIC_DISC_CAT;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.cmd.value();
    }
}
