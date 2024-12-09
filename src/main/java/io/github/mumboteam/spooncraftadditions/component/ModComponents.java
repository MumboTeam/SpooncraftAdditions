package io.github.mumboteam.spooncraftadditions.component;

import com.mojang.serialization.Codec;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static void initialize() {
    }

    public static final ComponentType<Boolean> SLIME_EXCITED = register(
            Identifier.of(SpooncraftAdditions.ID, "slime_excited"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    private static <T> ComponentType<T> register(Identifier id, ComponentType<T> entry) {
        ComponentType<T> componentType = Registry.register(Registries.DATA_COMPONENT_TYPE, id, entry);
        PolymerComponent.registerDataComponent(componentType);
        return componentType;
    }
}
