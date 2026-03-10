package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.entity.GiftBoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static final BlockEntityType<GiftBoxBlockEntity> GIFT_BOX = register(
            "gift_box",
            FabricBlockEntityTypeBuilder.create(GiftBoxBlockEntity::new, ModBlocks.GIFT_BOX).build()
    );

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        PolymerBlockUtils.registerBlockEntity(blockEntityType);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, path), blockEntityType);
    }

    public static void initialize() {
    }
}

