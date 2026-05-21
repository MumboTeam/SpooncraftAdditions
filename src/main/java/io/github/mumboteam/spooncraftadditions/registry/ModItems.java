package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.PolymerCreativeModeTabUtils;
import eu.pb4.polymer.core.api.item.PolymerHeadBlockItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ModItems {
    private ModItems() {}

    public static final Item SPOONCRAFT_SPOON = register("spooncraft_spoon", SpooncraftSpoon::new);
    public static final Item SPOONCRAFT_SWORD = register("spooncraft_sword", SpooncraftSword::new);
    public static final Item SLIME_BUCKET = register("slime_bucket", SlimeBucket::new);
    public static final Item SHRINK_RAY = register("shrink_ray", ShrinkRay::new);

    public static final Item GIFT_BOX = register("gift_box", (settings) -> new PolymerHeadBlockItem((Block & PolymerHeadBlock) ModBlocks.GIFT_BOX, settings));

    public static final Item CLAIM_SCROLL = register("claim_scroll", (settings -> new SimplePolymerItem(settings, Items.PAPER, true)));
    public static final Item CLAIM_CHECKER = register("claim_checker", (settings -> new SimplePolymerItem(settings, Items.PAPER, true)));

    public static final Item SKYBLOCK_HAT = register("skyblock_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "skyblock_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_SKYBLOCK_HAT = register("netherite_skyblock_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(37)), "skyblock_hat", ArmorMaterials.NETHERITE));
    public static final Item BALTOP_HAT = register("baltop_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "baltop_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_BALTOP_HAT = register("netherite_baltop_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "baltop_hat", ArmorMaterials.NETHERITE));
    public static final Item ARROW_HAT = register("arrow_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "arrow_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_ARROW_HAT = register("netherite_arrow_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "arrow_hat", ArmorMaterials.NETHERITE));
    public static final Item SKYBLOCK_STARTER_BASE_HAT = register("skyblock_starter_base_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "skyblock_starter_base_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_SKYBLOCK_STARTER_BASE_HAT = register("netherite_skyblock_starter_base_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "skyblock_starter_base_hat", ArmorMaterials.NETHERITE));
    public static final Item DEEP_VOID_SANCTUARY_HAT = register("deep_void_sanctuary_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "deep_void_sanctuary_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_DEEP_VOID_SANCTUARY_HAT = register("netherite_deep_void_sanctuary_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "deep_void_sanctuary_hat", ArmorMaterials.NETHERITE));
    public static final Item DUCK_HAT = register("duck_hat", (settings) -> new DuckHat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), ArmorMaterials.DIAMOND, ModEntityTypes.DUCK));
    public static final Item NETHERITE_DUCK_HAT = register("netherite_duck_hat", (settings) -> new DuckHat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), ArmorMaterials.NETHERITE, ModEntityTypes.NETHERITE_DUCK));
    public static final Item S5_SPAWN_HAT = register("s5_spawn_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "s5_spawn_hat", ArmorMaterials.DIAMOND)); //S5 starter base participation
    public static final Item NETHERITE_S5_SPAWN_HAT = register("netherite_s5_spawn_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "s5_spawn_hat", ArmorMaterials.NETHERITE));
    public static final Item WINDMILL_HOUSE_HAT = register("windmill_house_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "windmill_house_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_WINDMILL_HOUSE_HAT = register("netherite_windmill_house_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "windmill_house_hat", ArmorMaterials.NETHERITE));
    public static final Item TAG_FIRST = register("tag_first", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "tag_first", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_TAG_FIRST = register("netherite_tag_first", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "tag_first", ArmorMaterials.NETHERITE));
    public static final Item TAG_SECOND = register("tag_second", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "tag_second", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_TAG_SECOND = register("netherite_tag_second", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "tag_second", ArmorMaterials.NETHERITE));
    public static final Item TAG_THIRD = register("tag_third", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "tag_third", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_TAG_THIRD = register("netherite_tag_third", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "tag_third", ArmorMaterials.NETHERITE));
    public static final Item TAG_PARTICIPATION = register("tag_participation", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "tag_participation", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_TAG_PARTICIPATION = register("netherite_tag_participation", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "tag_participation", ArmorMaterials.NETHERITE));
    public static final Item REDSTONE_HAT = register("redstone_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "redstone_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_REDSTONE_HAT = register("netherite_redstone_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "redstone_hat", ArmorMaterials.NETHERITE));
    public static final Item PIRATE_HAT = register("pirate_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "pirate_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_PIRATE_HAT = register("netherite_pirate_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "pirate_hat", ArmorMaterials.NETHERITE));
    public static final Item WREATH_HAT = register("wreath", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "wreath", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_WREATH_HAT = register("netherite_wreath", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "wreath", ArmorMaterials.NETHERITE));
    public static final Item JESTER_HAT = register("jester_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "jester_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_JESTER_HAT = register("netherite_jester_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "jester_hat", ArmorMaterials.NETHERITE));
    public static final Item PUMPKIN_HAT = register("pumpkin_hat", (settings) -> new Hat(settings.rarity(Rarity.UNCOMMON).durability(ArmorType.HELMET.getDurability(33)), "pumpkin_hat", ArmorMaterials.DIAMOND));
    public static final Item NETHERITE_PUMPKIN_HAT = register("netherite_pumpkin_hat", (settings) -> new Hat(settings.fireResistant().rarity(Rarity.EPIC).durability(ArmorType.HELMET.getDurability(37)), "pumpkin_hat", ArmorMaterials.NETHERITE));

    public static final Item DRAGON_WINGS = register("dragon_wings", (settings -> new SimplePolymerItem(settings.durability(432).rarity(Rarity.EPIC).component(DataComponents.GLIDER, Unit.INSTANCE).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA).setAsset(ModEquipmentAssetKeys.DRAGON_WINGS).setDamageOnHurt(false).build()).repairable(Items.PHANTOM_MEMBRANE), Items.ELYTRA, true)));
    public static final Item KIRBY_WINGS = register("kirby_wings", (settings -> new SimplePolymerItem(settings.durability(432).rarity(Rarity.EPIC).component(DataComponents.GLIDER, Unit.INSTANCE).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA).setAsset(ModEquipmentAssetKeys.KIRBY_WINGS).setDamageOnHurt(false).build()).repairable(Items.PHANTOM_MEMBRANE), Items.ELYTRA, true)));


    public static final Item REDSTONE_STAFF = register("redstone_staff", settings -> new RedstoneStaff(settings.rarity(Rarity.UNCOMMON).sword(ToolMaterial.DIAMOND, 3.0F, -2.4F)));
    public static final Item NETHERITE_REDSTONE_STAFF = register("netherite_redstone_staff", settings -> new RedstoneStaff(settings.rarity(Rarity.EPIC).sword(ToolMaterial.NETHERITE, 3.0F, -2.4F).overrideDescription("item.spooncraftadditions.redstone_staff")));
    public static final Item TETRIO_STICK = register("tetrio_stick", TetrioStick::new);

    public static final Item EGG_BASKET = register("egg_basket", EggBasket::new);
    public static final Item EGG_MUMBO = register("eggmumbo", (settings -> new PolymerBlockItem(ModBlocks.EGG_MUMBO, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG1 = register("egg1", (settings -> new PolymerBlockItem(ModBlocks.EGG1, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG11 = register("egg11", (settings -> new PolymerBlockItem(ModBlocks.EGG11, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG12 = register("egg12", (settings -> new PolymerBlockItem(ModBlocks.EGG12, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG13 = register("egg13", (settings -> new PolymerBlockItem(ModBlocks.EGG13, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG2 = register("egg2", (settings -> new PolymerBlockItem(ModBlocks.EGG2, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG21 = register("egg21", (settings -> new PolymerBlockItem(ModBlocks.EGG21, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG22 = register("egg22", (settings -> new PolymerBlockItem(ModBlocks.EGG22, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG23 = register("egg23", (settings -> new PolymerBlockItem(ModBlocks.EGG23, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG3 = register("egg3", (settings -> new PolymerBlockItem(ModBlocks.EGG3, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG31 = register("egg31", (settings -> new PolymerBlockItem(ModBlocks.EGG31, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG32 = register("egg32", (settings -> new PolymerBlockItem(ModBlocks.EGG32, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));
    public static final Item EGG33 = register("egg33", (settings -> new PolymerBlockItem(ModBlocks.EGG33, settings.overrideDescription("item.spooncraftadditions.egg").stacksTo(64))));

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

    public static Item register(String path, Function<Item.Properties, Item> function) {
        Identifier id = Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, path);
        Item item = function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)));
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void initialize() {
        PolymerCreativeModeTabUtils.registerPolymerCreativeModeTab(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "items"), CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, -1)
                .icon(() -> {
                    ItemStack item = new ItemStack(Items.NETHERITE_SHOVEL);
                    item.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "spooncraft_spoon"));
                    return item;
                })
                .title(Component.translatable("itemGroup.spooncraftadditions"))
                .displayItems(((context, entries) -> {
                    entries.accept(SPOONCRAFT_SPOON);
                    entries.accept(SPOONCRAFT_SWORD);
                    entries.accept(SLIME_BUCKET);
                    entries.accept(SHRINK_RAY);

                    entries.accept(GIFT_BOX);

                    entries.accept(CLAIM_SCROLL);
                    entries.accept(CLAIM_CHECKER);

                    entries.accept(SKYBLOCK_HAT);
                    entries.accept(BALTOP_HAT);
                    entries.accept(ARROW_HAT);
                    entries.accept(SKYBLOCK_STARTER_BASE_HAT);
                    entries.accept(DEEP_VOID_SANCTUARY_HAT);
                    entries.accept(DUCK_HAT);
                    entries.accept(S5_SPAWN_HAT); // S5 Starter base participation
                    entries.accept(WINDMILL_HOUSE_HAT);
                    entries.accept(TAG_FIRST);
                    entries.accept(TAG_SECOND);
                    entries.accept(TAG_THIRD);
                    entries.accept(TAG_PARTICIPATION);
                    entries.accept(REDSTONE_HAT);
                    entries.accept(PIRATE_HAT);
                    entries.accept(WREATH_HAT);
                    entries.accept(JESTER_HAT);
                    entries.accept(PUMPKIN_HAT);

                    entries.accept(DRAGON_WINGS);
                    entries.accept(KIRBY_WINGS);

                    entries.accept(REDSTONE_STAFF);
                    entries.accept(TETRIO_STICK);
                })).build()
        );

        PolymerCreativeModeTabUtils.registerPolymerCreativeModeTab(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "discs"), CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, -1)
                .icon(() -> {
                    ItemStack item = new ItemStack(Items.PAPER);
                    item.set(DataComponents.ITEM_MODEL, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "music_disc_dibby_dibby_diamonds"));
                    return item;
                })
                .title(Component.translatable("itemGroup.spooncraftadditions.discs"))
                .displayItems(((context, entries) -> {
                    entries.accept(MUSIC_DISC_CACTUS_STRING_SAND);
                    entries.accept(MUSIC_DISC_CORRIDOR);
                    entries.accept(MUSIC_DISC_DIABOLICAL);
                    entries.accept(MUSIC_DISC_DIBBY_DIBBY_DIAMONDS);
                    entries.accept(MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO);
                    entries.accept(MUSIC_DISC_MORE_DOORS_FOR_ME);
                    entries.accept(MUSIC_DISC_MUMBO_AFK);
                    entries.accept(MUSIC_DISC_MUMBO_NO_NO_NO);
                    entries.accept(MUSIC_DISC_NETHER_WART);
                    entries.accept(MUSIC_DISC_OUTRO_TIME);
                    entries.accept(MUSIC_DISC_PICKLES);
                    entries.accept(MUSIC_DISC_PLACING_SLABS);
                    entries.accept(MUSIC_DISC_RESOURCE_GATHERING);
                    entries.accept(MUSIC_DISC_SHIFT_CLICK);
                    entries.accept(MUSIC_DISC_STUPID);
                    entries.accept(MUSIC_DISC_SUGARCANE);
                    entries.accept(MUSIC_DISC_THE_SUPER_WEAPON);
                    entries.accept(MUSIC_DISC_TINY_TIMELAPSE);
                    entries.accept(MUSIC_DISC_WATER_SOURCE);
                })).build()
        );

        PolymerCreativeModeTabUtils.registerPolymerCreativeModeTab(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "eggs"), CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, -1)
                .icon(() -> new ItemStack(ModItems.EGG_MUMBO))
                .title(Component.translatable("itemGroup.spooncraftadditions.eggs"))
                .displayItems(((context, entries) -> {
                    entries.accept(EGG_BASKET);
                    entries.accept(EGG_MUMBO);
                    entries.accept(EGG1);
                    entries.accept(EGG11);
                    entries.accept(EGG12);
                    entries.accept(EGG13);
                    entries.accept(EGG2);
                    entries.accept(EGG21);
                    entries.accept(EGG22);
                    entries.accept(EGG23);
                    entries.accept(EGG3);
                    entries.accept(EGG31);
                    entries.accept(EGG32);
                    entries.accept(EGG33);
                })).build()
        );
    }
}
