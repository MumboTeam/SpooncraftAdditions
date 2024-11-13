package io.github.mumboteam.spooncraftadditions.loot;

import io.github.mumboteam.spooncraftadditions.registry.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;

public class ModLootTables {
    public static void initialize() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin() && (LootTables.SIMPLE_DUNGEON_CHEST.equals(key) || LootTables.ANCIENT_CITY_CHEST.equals(key) || LootTables.WOODLAND_MANSION_CHEST.equals(key))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .conditionally(RandomChanceLootCondition.builder(0.3F))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_CACTUS_STRING_SAND))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_CORRIDOR))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_DIABOLICAL))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_DIBBY_DIBBY_DIAMONDS))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_KERALIS_AND_XISUMA_NO_NO_NO))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_MORE_DOORS_FOR_ME))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_MUMBO_AFK))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_MUMBO_NO_NO_NO))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_NETHER_WART))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_OUTRO_TIME))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_PICKLES))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_PLACING_SLABS))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_RESOURCE_GATHERING))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_SHIFT_CLICK))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_STUPID))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_SUGARCANE))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_THE_SUPER_WEAPON))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_TINY_TIMELAPSE))
                        .with(ItemEntry.builder(ModItems.MUSIC_DISC_WATER_SOURCE));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
