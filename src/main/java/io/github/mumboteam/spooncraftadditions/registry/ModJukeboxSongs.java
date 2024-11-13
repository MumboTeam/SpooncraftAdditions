package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.block.jukebox.JukeboxSongs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface ModJukeboxSongs extends JukeboxSongs {
    public static void initialize() {
    }

    RegistryKey<JukeboxSong> CACTUS_STRING_SAND = of("cactus_string_sand");
    RegistryKey<JukeboxSong> CORRIDOR = of("corridor");
    RegistryKey<JukeboxSong> DIABOLICAL = of("diabolical");
    RegistryKey<JukeboxSong> DIBBY_DIBBY_DIAMONDS = of("dibby_dibby_diamonds");
    RegistryKey<JukeboxSong> KERALIS_AND_XISUMA_NO_NO_NO = of("keralis_and_xisuma_no_no_no");
    RegistryKey<JukeboxSong> MORE_DOORS_FOR_ME = of("more_doors_for_me");
    RegistryKey<JukeboxSong> MUMBO_AFK = of("mumbo_afk");
    RegistryKey<JukeboxSong> MUMBO_NO_NO_NO = of("mumbo_no_no_no");
    RegistryKey<JukeboxSong> NETHER_WART = of("nether_wart");
    RegistryKey<JukeboxSong> OUTRO_TIME = of("outro_time");
    RegistryKey<JukeboxSong> PICKLES = of("pickles");
    RegistryKey<JukeboxSong> PLACING_SLABS = of("placing_slabs");
    RegistryKey<JukeboxSong> RESOURCE_GATHERING = of("resource_gathering");
    RegistryKey<JukeboxSong> SHIFT_CLICK = of("shift_click");
    RegistryKey<JukeboxSong> STUPID = of("stupid");
    RegistryKey<JukeboxSong> SUGARCANE = of("sugarcane");
    RegistryKey<JukeboxSong> THE_SUPER_WEAPON = of("the_super_weapon");
    RegistryKey<JukeboxSong> TINY_TIMELAPSE = of("tiny_timelapse");
    RegistryKey<JukeboxSong> WATER_SOURCE = of("water_source");

    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(SpooncraftAdditions.ID, id));
    }
}
