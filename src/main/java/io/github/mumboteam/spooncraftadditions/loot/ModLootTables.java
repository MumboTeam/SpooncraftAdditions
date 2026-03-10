package io.github.mumboteam.spooncraftadditions.loot;

import io.github.mumboteam.spooncraftadditions.registry.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class ModLootTables {
    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && (BuiltInLootTables.SIMPLE_DUNGEON.equals(key) || BuiltInLootTables.ANCIENT_CITY.equals(key) || BuiltInLootTables.WOODLAND_MANSION.equals(key))) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .when(LootItemRandomChanceCondition.randomChance(0.3F))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_CACTUS_STRING_SAND))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_CORRIDOR))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_DIABOLICAL))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_DIBBY_DIBBY_DIAMONDS))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_MORE_DOORS_FOR_ME))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_MUMBO_AFK))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_MUMBO_NO_NO_NO))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_NETHER_WART))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_OUTRO_TIME))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_PICKLES))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_PLACING_SLABS))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_RESOURCE_GATHERING))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_SHIFT_CLICK))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_STUPID))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_SUGARCANE))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_THE_SUPER_WEAPON))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_TINY_TIMELAPSE))
                        .add(LootItem.lootTableItem(ModItems.MUSIC_DISC_WATER_SOURCE));
                tableBuilder.withPool(poolBuilder);
            }
        });
    }
}
