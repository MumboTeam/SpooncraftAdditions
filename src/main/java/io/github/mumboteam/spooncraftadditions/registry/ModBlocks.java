package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.GiftBoxBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block GIFT_BOX = register(new GiftBoxBlock(AbstractBlock.Settings.create()), "gift_box");

    public static Block register(Block block, String path) {
        // Register the block and its item.
        Identifier id = Identifier.of(SpooncraftAdditions.ID, path);

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}