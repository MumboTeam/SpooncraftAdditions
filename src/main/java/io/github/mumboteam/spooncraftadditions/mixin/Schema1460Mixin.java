package io.github.mumboteam.spooncraftadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.datafixer.schema.Schema1460;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(Schema1460.class)
public class Schema1460Mixin {
    @Inject(method = "registerEntities", at = @At("TAIL"))
    private void registerEntities(Schema schema, CallbackInfoReturnable<Map<String, Supplier<TypeTemplate>>> cir, @Local Map<String, Supplier<TypeTemplate>> map) {
        schema.registerSimple(map, "spooncraftadditions:duck");
        schema.registerSimple(map, "spooncraftadditions:netherite_duck");
    }

    @Inject(method = "registerBlockEntities", at = @At("TAIL"))
    private void registerBlockEntities(Schema schema, CallbackInfoReturnable<Map<String, Supplier<TypeTemplate>>> cir, @Local Map<String, Supplier<TypeTemplate>> map) {
        schema.registerSimple(map, "spooncraftadditions:gift_box");
    }
}