package io.github.mumboteam.spooncraftadditions.registry;

import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.entity.DuckEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final EntityType<DuckEntity> DUCK = register(
            "duck",
            EntityType.Builder.create(getDuckFactory(() -> ModItems.DUCK_HAT), SpawnGroup.MISC).dimensions(0.375F, 0.5625F)
    );

    public static final EntityType<DuckEntity> NETHERITE_DUCK = register(
            "netherite_duck",
            EntityType.Builder.create(getDuckFactory(() -> ModItems.NETHERITE_DUCK_HAT), SpawnGroup.MISC).dimensions(0.375F, 0.5625F)
    );

    public static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
        Identifier id = Identifier.of(SpooncraftAdditions.ID, path);
        EntityType<T> entityType =  Registry.register(Registries.ENTITY_TYPE, id, builder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)));
        PolymerEntityUtils.registerType(entityType);
        return entityType;
    }

    private static EntityType.EntityFactory<DuckEntity> getDuckFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new DuckEntity(type, world, itemSupplier);
    }

    public static void initialize() {
    }
}
