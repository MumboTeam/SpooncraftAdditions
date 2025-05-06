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

    public static final Block EGG_MUMBO = register("eggmumbo", EggBlock::new);
    public static final Block EGG1 = register("egg1", EggBlock::new);
    public static final Block EGG11 = register("egg11", EggBlock::new);
    public static final Block EGG12 = register("egg12", EggBlock::new);
    public static final Block EGG13 = register("egg13", EggBlock::new);
    public static final Block EGG2 = register("egg2", EggBlock::new);
    public static final Block EGG21 = register("egg21", EggBlock::new);
    public static final Block EGG22 = register("egg22", EggBlock::new);
    public static final Block EGG23 = register("egg23", EggBlock::new);
    public static final Block EGG3 = register("egg3", EggBlock::new);
    public static final Block EGG31 = register("egg31", EggBlock::new);
    public static final Block EGG32 = register("egg32", EggBlock::new);
    public static final Block EGG33 = register("egg33", EggBlock::new);

    public static Block register(String path, Function<AbstractBlock.Settings, Block> function) {
        Identifier id = Identifier.of(SpooncraftAdditions.ID, path);
        Block block = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {}
}