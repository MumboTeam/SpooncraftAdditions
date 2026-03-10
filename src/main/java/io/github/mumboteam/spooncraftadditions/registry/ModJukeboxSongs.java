package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;

public interface ModJukeboxSongs extends JukeboxSongs {
    public static void initialize() {
    }

    ResourceKey<JukeboxSong> CACTUS_STRING_SAND = create("cactus_string_sand");
    ResourceKey<JukeboxSong> CORRIDOR = create("corridor");
    ResourceKey<JukeboxSong> DIABOLICAL = create("diabolical");
    ResourceKey<JukeboxSong> DIBBY_DIBBY_DIAMONDS = create("dibby_dibby_diamonds");
    ResourceKey<JukeboxSong> KERALIS_AND_XISUMA_NO_NO_NO = create("keralis_and_xisuma_no_no_no");
    ResourceKey<JukeboxSong> MORE_DOORS_FOR_ME = create("more_doors_for_me");
    ResourceKey<JukeboxSong> MUMBO_AFK = create("mumbo_afk");
    ResourceKey<JukeboxSong> MUMBO_NO_NO_NO = create("mumbo_no_no_no");
    ResourceKey<JukeboxSong> NETHER_WART = create("nether_wart");
    ResourceKey<JukeboxSong> OUTRO_TIME = create("outro_time");
    ResourceKey<JukeboxSong> PICKLES = create("pickles");
    ResourceKey<JukeboxSong> PLACING_SLABS = create("placing_slabs");
    ResourceKey<JukeboxSong> RESOURCE_GATHERING = create("resource_gathering");
    ResourceKey<JukeboxSong> SHIFT_CLICK = create("shift_click");
    ResourceKey<JukeboxSong> STUPID = create("stupid");
    ResourceKey<JukeboxSong> SUGARCANE = create("sugarcane");
    ResourceKey<JukeboxSong> THE_SUPER_WEAPON = create("the_super_weapon");
    ResourceKey<JukeboxSong> TINY_TIMELAPSE = create("tiny_timelapse");
    ResourceKey<JukeboxSong> WATER_SOURCE = create("water_source");

    private static ResourceKey<JukeboxSong> create(String id) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, id));
    }
}
