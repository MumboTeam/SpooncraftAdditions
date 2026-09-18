package io.github.mumboteam.spooncraftadditions.datagen;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class SpooncraftAdditionsBlockLootTableProvider extends FabricBlockLootSubProvider {
    public SpooncraftAdditionsBlockLootTableProvider(FabricPackOutput fabricPackOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(fabricPackOutput, registriesFuture);
    }

    @Override
    public void generate() {
        for (Block block : BuiltInRegistries.BLOCK) {
            if (BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(SpooncraftAdditions.ID)) {
                dropSelf(block);
            }
        }
    }
}
