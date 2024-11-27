package io.github.mumboteam.spooncraftadditions.mixin;

import net.minecraft.entity.vehicle.AbstractBoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBoatEntity.class)
public interface AbstractBoatEntityAccessor {
    @Invoker
    void callUpdateVelocity();
}
