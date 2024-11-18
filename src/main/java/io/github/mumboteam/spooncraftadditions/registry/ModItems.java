package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerHeadBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.item.*;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems {
    private ModItems() {
    }

    public static final Item SPOONCRAFT_SPOON = register("spooncraft_spoon", (settings) -> new SpooncraftSpoon(settings));
    public static final Item SPOONCRAFT_SWORD = register("spooncraft_sword", (settings) -> new SpooncraftSword(settings));
    public static final Item SLIME_BUCKET = register("slime_bucket", (settings) -> new SlimeBucket(settings));

    public static final Item GIFT_BOX = register("gift_box", (settings) -> new PolymerHeadBlockItem((Block & PolymerHeadBlock) ModBlocks.GIFT_BOX, settings));

    public static final Item SKYBLOCK_HAT = register("skyblock_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).maxDamage(EquipmentType.HELMET.getMaxDamage(33)), "skyblock_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_SKYBLOCK_HAT = register("netherite_skyblock_hat", (settings) -> new Hat(settings.fireproof().rarity(Rarity.UNCOMMON).maxDamage(EquipmentType.HELMET.getMaxDamage(37)), "skyblock_hat", ArmorMaterials.NETHERITE));
    public static final Item BALTOP_HAT = register("baltop_hat", (settings) -> new Hat(settings.rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(33)), "baltop_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_BALTOP_HAT = register("netherite_baltop_hat", (settings) -> new Hat(settings.fireproof().rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(37)), "baltop_hat", ArmorMaterials.NETHERITE));
    public static final Item ARROW_HAT = register("arrow_hat", (settings) -> new Hat(settings.rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(33)), "arrow_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_ARROW_HAT = register("netherite_arrow_hat", (settings) -> new Hat(settings.fireproof().rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(37)), "arrow_hat", ArmorMaterials.NETHERITE));
    public static final Item SKYBLOCK_STARTER_BASE_HAT = register("skyblock_starter_base_hat", (settings) -> new Hat(settings.rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(33)), "skyblock_starter_base_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_SKYBLOCK_STARTER_BASE_HAT = register("netherite_skyblock_starter_base_hat", (settings) -> new Hat(settings.fireproof().rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(37)), "skyblock_starter_base_hat", ArmorMaterials.NETHERITE));
    public static final Item DEEP_VOID_SANCTUARY_HAT = register("deep_void_sanctuary_hat", (settings) -> new Hat(settings.rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(33)), "deep_void_sanctuary_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_DEEP_VOID_SANCTUARY_HAT = register("netherite_deep_void_sanctuary_hat", (settings) -> new Hat(settings.fireproof().rarity(Rarity.EPIC).maxDamage(EquipmentType.HELMET.getMaxDamage(37)), "deep_void_sanctuary_hat", ArmorMaterials.NETHERITE));

    public static final Item MUSIC_DISC_CACTUS_STRING_SAND = register("music_disc_cactus_string_sand", (settings) -> new MusicDisc(settings, "cactus_string_sand", ModJukeboxSongs.CACTUS_STRING_SAND));
    public static final Item MUSIC_DISC_CORRIDOR = register("music_disc_corridor", (settings) -> new MusicDisc(settings, "corridor", ModJukeboxSongs.CORRIDOR));
    public static final Item MUSIC_DISC_DIABOLICAL = register("music_disc_diabolical", (settings) -> new MusicDisc(settings, "diabolical", ModJukeboxSongs.DIABOLICAL));
    public static final Item MUSIC_DISC_DIBBY_DIBBY_DIAMONDS = register("music_disc_dibby_dibby_diamonds", (settings) -> new MusicDisc(settings, "dibby_dibby_diamonds", ModJukeboxSongs.DIBBY_DIBBY_DIAMONDS));
    public static final Item MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO = register("music_disc_keralis_and_xisuma_no_no_no", (settings) -> new MusicDisc(settings, "keralis_and_xisuma_no_no_no", ModJukeboxSongs.KERALIS_AND_XISUMA_NO_NO_NO));
    public static final Item MUSIC_DISC_MORE_DOORS_FOR_ME = register("music_disc_more_doors_for_me", (settings) -> new MusicDisc(settings, "more_doors_for_me", ModJukeboxSongs.MORE_DOORS_FOR_ME));
    public static final Item MUSIC_DISC_MUMBO_AFK = register("music_disc_mumbo_afk", (settings) -> new MusicDisc(settings, "mumbo_afk", ModJukeboxSongs.MUMBO_AFK));
    public static final Item MUSIC_DISC_MUMBO_NO_NO_NO = register("music_disc_mumbo_no_no_no", (settings) -> new MusicDisc(settings, "mumbo_no_no_no", ModJukeboxSongs.MUMBO_NO_NO_NO));
    public static final Item MUSIC_DISC_NETHER_WART = register("music_disc_nether_wart", (settings) -> new MusicDisc(settings, "nether_wart", ModJukeboxSongs.NETHER_WART));
    public static final Item MUSIC_DISC_OUTRO_TIME = register("music_disc_outro_time", (settings) -> new MusicDisc(settings, "outro_time", ModJukeboxSongs.OUTRO_TIME));
    public static final Item MUSIC_DISC_PICKLES = register("music_disc_pickles", (settings) -> new MusicDisc(settings, "pickles", ModJukeboxSongs.PICKLES));
    public static final Item MUSIC_DISC_PLACING_SLABS = register("music_disc_placing_slabs", (settings) -> new MusicDisc(settings, "placing_slabs", ModJukeboxSongs.PLACING_SLABS));
    public static final Item MUSIC_DISC_RESOURCE_GATHERING = register("music_disc_resource_gathering", (settings) -> new MusicDisc(settings, "resource_gathering", ModJukeboxSongs.RESOURCE_GATHERING));
    public static final Item MUSIC_DISC_SHIFT_CLICK = register("music_disc_shift_click", (settings) -> new MusicDisc(settings, "shift_click", ModJukeboxSongs.SHIFT_CLICK));
    public static final Item MUSIC_DISC_STUPID = register("music_disc_stupid", (settings) -> new MusicDisc(settings, "stupid", ModJukeboxSongs.STUPID));
    public static final Item MUSIC_DISC_SUGARCANE = register("music_disc_sugarcane", (settings) -> new MusicDisc(settings, "sugarcane", ModJukeboxSongs.SUGARCANE));
    public static final Item MUSIC_DISC_THE_SUPER_WEAPON = register("music_disc_the_super_weapon", (settings) -> new MusicDisc(settings, "the_super_weapon", ModJukeboxSongs.THE_SUPER_WEAPON));
    public static final Item MUSIC_DISC_TINY_TIMELAPSE = register("music_disc_tiny_timelapse", (settings) -> new MusicDisc(settings, "tiny_timelapse", ModJukeboxSongs.TINY_TIMELAPSE));
    public static final Item MUSIC_DISC_WATER_SOURCE = register("music_disc_water_source", (settings) -> new MusicDisc(settings, "water_source", ModJukeboxSongs.WATER_SOURCE));

    public static Item register(String path, Function<Item.Settings, Item> function) {
        Identifier id = Identifier.of(SpooncraftAdditions.ID, path);
        Item item = function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void initialize() {
        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(SpooncraftAdditions.ID, "items"), ItemGroup.create(ItemGroup.Row.BOTTOM, -1)
                .icon(() -> {
                    ItemStack item = new ItemStack(Items.NETHERITE_SHOVEL);
                    Identifier cmd = PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_spoon"));
                    item.set(DataComponentTypes.ITEM_MODEL, cmd);
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
