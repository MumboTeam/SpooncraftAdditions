package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.entity.DuckEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final EntityType<DuckEntity> DUCK = register(
            "duck",
            EntityType.Builder.of(getDuckFactory(() -> ModItems.DUCK_HAT), MobCategory.MISC).sized(0.375F, 0.5625F)
    );

    public static final EntityType<DuckEntity> NETHERITE_DUCK = register(
            "netherite_duck",
            EntityType.Builder.of(getDuckFactory(() -> ModItems.NETHERITE_DUCK_HAT), MobCategory.MISC).sized(0.375F, 0.5625F)
    );

    public static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
        Identifier id = Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, path);
        EntityType<T> entityType =  Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(ResourceKey.create(Registries.ENTITY_TYPE, id)));
        PolymerEntityUtils.registerType(entityType);
        return entityType;
    }

    private static EntityType.EntityFactory<DuckEntity> getDuckFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new DuckEntity(type, world, itemSupplier);
    }

    public static void initialize() {
    }
}
