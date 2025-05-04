package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.GiftBoxBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import io.github.mumboteam.spooncraftadditions.block.EggBlock;

import java.util.function.Function;

public class ModBlocks {
    public static final Block GIFT_BOX = register("gift_box", GiftBoxBlock::new);

    public static final Block EGG_MUMBO = register("eggs/eggmumbo", EggBlock::new);
    public static final Block EGG1 = register("eggs/egg1", EggBlock::new);
    public static final Block EGG11 = register("eggs/egg11", EggBlock::new);
    public static final Block EGG12 = register("eggs/egg12", EggBlock::new);
    public static final Block EGG13 = register("eggs/egg13", EggBlock::new);
    public static final Block EGG2 = register("eggs/egg2", EggBlock::new);
    public static final Block EGG21 = register("eggs/egg21", EggBlock::new);
    public static final Block EGG22 = register("eggs/egg22", EggBlock::new);
    public static final Block EGG23 = register("eggs/egg23", EggBlock::new);
    public static final Block EGG3 = register("eggs/egg3", EggBlock::new);
    public static final Block EGG31 = register("eggs/egg31", EggBlock::new);
    public static final Block EGG32 = register("eggs/egg32", EggBlock::new);
    public static final Block EGG33 = register("eggs/egg33", EggBlock::new);

    public static Block register(String path, Function<AbstractBlock.Settings, Block> function) {
        // Register the block and its item.
        Identifier id = Identifier.of(SpooncraftAdditions.ID, path);
        Block block = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}