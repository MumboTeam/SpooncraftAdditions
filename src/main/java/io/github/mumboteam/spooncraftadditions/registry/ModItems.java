package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerHeadBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.item.*;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    private ModItems() {
    }

    public static final Item SPOONCRAFT_SPOON = register(new SpooncraftSpoon(new Item.Settings()), "spooncraft_spoon");
    public static final Item SPOONCRAFT_SWORD = register(new SpooncraftSword(new Item.Settings()), "spooncraft_sword");
    public static final Item SLIME_BUCKET = register(new SlimeBucket(new Item.Settings()), "slime_bucket");

    public static final Item GIFT_BOX = register(new PolymerHeadBlockItem((Block & PolymerHeadBlock) ModBlocks.GIFT_BOX, new Item.Settings()), "gift_box");

    public static final Item SKYBLOCK_HAT = register(new Hat(new Item.Settings().rarity(Rarity.UNCOMMON).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33)), "skyblock_hat", ArmorMaterials.DIAMOND), "skyblock_hat");
    public static final Item NETHERITE_SKYBLOCK_HAT = register(new Hat(new Item.Settings().fireproof().rarity(Rarity.UNCOMMON).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37)), "skyblock_hat", ArmorMaterials.NETHERITE), "netherite_skyblock_hat");
    public static final Item BALTOP_HAT = register(new Hat(new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33)), "baltop_hat", ArmorMaterials.DIAMOND), "baltop_hat");
    public static final Item NETHERITE_BALTOP_HAT = register(new Hat(new Item.Settings().fireproof().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37)), "baltop_hat", ArmorMaterials.NETHERITE), "netherite_baltop_hat");
    public static final Item ARROW_HAT = register(new Hat(new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33)), "arrow_hat", ArmorMaterials.DIAMOND), "arrow_hat");
    public static final Item NETHERITE_ARROW_HAT = register(new Hat(new Item.Settings().fireproof().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37)), "arrow_hat", ArmorMaterials.NETHERITE), "netherite_arrow_hat");
    public static final Item SKYBLOCK_STARTER_BASE_HAT = register(new Hat(new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33)), "skyblock_starter_base_hat", ArmorMaterials.DIAMOND), "skyblock_starter_base_hat");
    public static final Item NETHERITE_SKYBLOCK_STARTER_BASE_HAT = register(new Hat(new Item.Settings().fireproof().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37)), "skyblock_starter_base_hat", ArmorMaterials.NETHERITE), "netherite_skyblock_starter_base_hat");
    public static final Item DEEP_VOID_SANCTUARY_HAT = register(new Hat(new Item.Settings().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33)), "deep_void_sanctuary_hat", ArmorMaterials.DIAMOND), "deep_void_sanctuary_hat");
    public static final Item NETHERITE_DEEP_VOID_SANCTUARY_HAT = register(new Hat(new Item.Settings().fireproof().rarity(Rarity.EPIC).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37)), "deep_void_sanctuary_hat", ArmorMaterials.NETHERITE), "netherite_deep_void_sanctuary_hat");

    public static final Item MUSIC_DISC_CACTUS_STRING_SAND = register(new MusicDisc(new Item.Settings(), "cactus_string_sand", ModJukeboxSongs.CACTUS_STRING_SAND), "music_disc_cactus_string_sand");
    public static final Item MUSIC_DISC_CORRIDOR = register(new MusicDisc(new Item.Settings(), "corridor", ModJukeboxSongs.CORRIDOR), "music_disc_corridor");
    public static final Item MUSIC_DISC_DIABOLICAL = register(new MusicDisc(new Item.Settings(), "diabolical", ModJukeboxSongs.DIABOLICAL), "music_disc_diabolical");
    public static final Item MUSIC_DISC_DIBBY_DIBBY_DIAMONDS = register(new MusicDisc(new Item.Settings(), "dibby_dibby_diamonds", ModJukeboxSongs.DIBBY_DIBBY_DIAMONDS), "music_disc_dibby_dibby_diamonds");
    public static final Item MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO = register(new MusicDisc(new Item.Settings(), "keralis_and_xisuma_no_no_no", ModJukeboxSongs.KERALIS_AND_XISUMA_NO_NO_NO), "music_disc_keralis_and_xisuma_no_no_no");
    public static final Item MUSIC_DISC_MORE_DOORS_FOR_ME = register(new MusicDisc(new Item.Settings(), "more_doors_for_me", ModJukeboxSongs.MORE_DOORS_FOR_ME), "music_disc_more_doors_for_me");
    public static final Item MUSIC_DISC_MUMBO_AFK = register(new MusicDisc(new Item.Settings(), "mumbo_afk", ModJukeboxSongs.MUMBO_AFK), "music_disc_mumbo_afk");
    public static final Item MUSIC_DISC_MUMBO_NO_NO_NO = register(new MusicDisc(new Item.Settings(), "mumbo_no_no_no", ModJukeboxSongs.MUMBO_NO_NO_NO), "music_disc_mumbo_no_no_no");
    public static final Item MUSIC_DISC_NETHER_WART = register(new MusicDisc(new Item.Settings(), "nether_wart", ModJukeboxSongs.NETHER_WART), "music_disc_nether_wart");
    public static final Item MUSIC_DISC_OUTRO_TIME = register(new MusicDisc(new Item.Settings(), "outro_time", ModJukeboxSongs.OUTRO_TIME), "music_disc_outro_time");
    public static final Item MUSIC_DISC_PICKLES = register(new MusicDisc(new Item.Settings(), "pickles", ModJukeboxSongs.PICKLES), "music_disc_pickles");
    public static final Item MUSIC_DISC_PLACING_SLABS = register(new MusicDisc(new Item.Settings(), "placing_slabs", ModJukeboxSongs.PLACING_SLABS), "music_disc_placing_slabs");
    public static final Item MUSIC_DISC_RESOURCE_GATHERING = register(new MusicDisc(new Item.Settings(), "resource_gathering", ModJukeboxSongs.RESOURCE_GATHERING), "music_disc_resource_gathering");
    public static final Item MUSIC_DISC_SHIFT_CLICK = register(new MusicDisc(new Item.Settings(), "shift_click", ModJukeboxSongs.SHIFT_CLICK), "music_disc_shift_click");
    public static final Item MUSIC_DISC_STUPID = register(new MusicDisc(new Item.Settings(), "stupid", ModJukeboxSongs.STUPID), "music_disc_stupid");
    public static final Item MUSIC_DISC_SUGARCANE = register(new MusicDisc(new Item.Settings(), "sugarcane", ModJukeboxSongs.SUGARCANE), "music_disc_sugarcane");
    public static final Item MUSIC_DISC_THE_SUPER_WEAPON = register(new MusicDisc(new Item.Settings(), "the_super_weapon", ModJukeboxSongs.THE_SUPER_WEAPON), "music_disc_the_super_weapon");
    public static final Item MUSIC_DISC_TINY_TIMELAPSE = register(new MusicDisc(new Item.Settings(), "tiny_timelapse", ModJukeboxSongs.TINY_TIMELAPSE), "music_disc_tiny_timelapse");
    public static final Item MUSIC_DISC_WATER_SOURCE = register(new MusicDisc(new Item.Settings(), "water_source", ModJukeboxSongs.WATER_SOURCE), "music_disc_water_source");

    public static Item register(Item instance, String path) {
        return Registry.register(Registries.ITEM, Identifier.of(SpooncraftAdditions.ID, path), instance);
    }

    public static void initialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(SpooncraftAdditions.ID, "items"), ItemGroup.create(ItemGroup.Row.BOTTOM, -1)
                .icon(() -> {
                    ItemStack item = new ItemStack(Items.NETHERITE_SHOVEL);
                    PolymerModelData cmd = PolymerResourcePackUtils.requestModel(Items.NETHERITE_SHOVEL, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_spoon"));
                    item.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(cmd.value()));
                    return item;
                })
                .displayName(Text.translatable("itemGroup.spooncraftadditions"))
                .entries(((context, entries) -> {
                    entries.add(SPOONCRAFT_SPOON);
                    entries.add(SPOONCRAFT_SWORD);
                    entries.add(SLIME_BUCKET);
                    entries.add(SKYBLOCK_HAT);
                    entries.add(BALTOP_HAT);
                    entries.add(ARROW_HAT);
                    entries.add(SKYBLOCK_STARTER_BASE_HAT);
                    entries.add(DEEP_VOID_SANCTUARY_HAT);
                    entries.add(GIFT_BOX);
                    entries.add(MUSIC_DISC_CACTUS_STRING_SAND);
                    entries.add(MUSIC_DISC_CORRIDOR);
                    entries.add(MUSIC_DISC_DIABOLICAL);
                    entries.add(MUSIC_DISC_DIBBY_DIBBY_DIAMONDS);
                    entries.add(MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO);
                    entries.add(MUSIC_DISC_MORE_DOORS_FOR_ME);
                    entries.add(MUSIC_DISC_MUMBO_AFK);
                    entries.add(MUSIC_DISC_MUMBO_NO_NO_NO);
                    entries.add(MUSIC_DISC_NETHER_WART);
                    entries.add(MUSIC_DISC_OUTRO_TIME);
                    entries.add(MUSIC_DISC_PICKLES);
                    entries.add(MUSIC_DISC_PLACING_SLABS);
                    entries.add(MUSIC_DISC_RESOURCE_GATHERING);
                    entries.add(MUSIC_DISC_SHIFT_CLICK);
                    entries.add(MUSIC_DISC_STUPID);
                    entries.add(MUSIC_DISC_SUGARCANE);
                    entries.add(MUSIC_DISC_THE_SUPER_WEAPON);
                    entries.add(MUSIC_DISC_TINY_TIMELAPSE);
                    entries.add(MUSIC_DISC_WATER_SOURCE);
                })).build()
        );
    }
}
