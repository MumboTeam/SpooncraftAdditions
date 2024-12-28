package io.github.mumboteam.spooncraftadditions.registry;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public interface ModEquipmentAssetKeys {
    RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));

    RegistryKey<EquipmentAsset> DRAGON_WINGS = register("dragon_wings");
    RegistryKey<EquipmentAsset> KIRBY_WINGS = register("kirby_wings");

    static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, Identifier.of(SpooncraftAdditions.ID, name));
    }
}