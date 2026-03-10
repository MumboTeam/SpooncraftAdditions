package io.github.mumboteam.spooncraftadditions.component;

import com.mojang.serialization.Codec;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponents {
    public static void initialize() {
    }

    public static final DataComponentType<Boolean> SLIME_EXCITED = register(
            Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "slime_excited"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );

    private static <T> DataComponentType<T> register(Identifier id, DataComponentType<T> entry) {
        DataComponentType<T> componentType = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id, entry);
        PolymerComponent.registerDataComponent(componentType);
        return componentType;
    }
}
