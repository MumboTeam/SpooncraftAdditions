package io.github.mumboteam.spooncraftadditions.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jspecify.annotations.NonNull;

public class SpooncraftAdditionsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(SpooncraftAdditionsRecipeProvider::new);
        pack.addProvider(SpooncraftAdditionsItemTagProvider::new);
        pack.addProvider(SpooncraftAdditionsBlockLootTableProvider::new);
    }
}
