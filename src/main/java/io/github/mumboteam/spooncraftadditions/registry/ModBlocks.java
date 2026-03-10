package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.EggBlock;
import io.github.mumboteam.spooncraftadditions.block.GiftBoxBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

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

    public static Block register(String path, Function<BlockBehaviour.Properties, Block> function) {
        Identifier id = Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, path);
        Block block = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, id)));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize() {}
}