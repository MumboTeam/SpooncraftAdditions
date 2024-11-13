package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.entity.GiftBoxBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static final BlockEntityType<GiftBoxBlockEntity> GIFT_BOX = register(
            "gift_box",
            BlockEntityType.Builder.create(GiftBoxBlockEntity::new, ModBlocks.GIFT_BOX).build()
    );

    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        PolymerBlockUtils.registerBlockEntity(blockEntityType);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(SpooncraftAdditions.ID, path), blockEntityType);
    }

    public static void initialize() {
    }
}

