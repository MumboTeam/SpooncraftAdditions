package io.github.mumboteam.spooncraftadditions.mixin;

import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBoat.class)
public interface AbstractBoatAccessor {
    @Invoker
    void callFloatBoat();
}
