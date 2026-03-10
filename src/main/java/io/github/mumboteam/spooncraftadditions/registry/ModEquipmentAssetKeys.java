package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public interface ModEquipmentAssetKeys {
    ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    ResourceKey<EquipmentAsset> DRAGON_WINGS = register("dragon_wings");
    ResourceKey<EquipmentAsset> KIRBY_WINGS = register("kirby_wings");

    static ResourceKey<EquipmentAsset> register(String name) {
        return ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, name));
    }
}