package io.github.mumboteam.spooncraftadditions.datagen;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.item.DuckHat;
import io.github.mumboteam.spooncraftadditions.item.Hat;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class SpooncraftAdditionsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public static final TagKey<Item> HATS_TAG = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "hats"));

    public SpooncraftAdditionsItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider arg) {
        var builder = builder(HATS_TAG);
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof Hat || item instanceof DuckHat) {
                BuiltInRegistries.ITEM.getResourceKey(item).ifPresent(builder::add);
            }
        }
    }
}
