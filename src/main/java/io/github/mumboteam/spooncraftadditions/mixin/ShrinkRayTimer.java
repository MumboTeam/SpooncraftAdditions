package io.github.mumboteam.spooncraftadditions.mixin;

import com.mojang.authlib.GameProfile;
import io.github.mumboteam.spooncraftadditions.accessor.ShrinkRayTimerAccess;
import io.github.mumboteam.spooncraftadditions.item.ShrinkRay;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ShrinkRayTimer extends Player implements ShrinkRayTimerAccess {
    @Unique
    private int ticksUntilBigAgain = -1;

    public ShrinkRayTimer(Level world, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (this.ticksUntilBigAgain != -1) {
            this.ticksUntilBigAgain--;

            if (this.ticksUntilBigAgain > 0 && this.ticksUntilBigAgain < ShrinkRay.GROW_TICKS) { // grow
               this.getAttributes().getInstance(Attributes.SCALE).setBaseValue(ShrinkRay.SIZE+((1-ShrinkRay.SIZE)*(1.0-((double) this.ticksUntilBigAgain /ShrinkRay.GROW_TICKS))));
            } else if (this.ticksUntilBigAgain > ShrinkRay.DURATION_TICKS-ShrinkRay.SHRINK_TICKS) { // shrink
                int ticksSinceStart = ShrinkRay.DURATION_TICKS - this.ticksUntilBigAgain;
                this.getAttributes().getInstance(Attributes.SCALE).setBaseValue(ShrinkRay.SIZE+((1-ShrinkRay.SIZE)*(1.0-((double) ticksSinceStart /ShrinkRay.SHRINK_TICKS))));
            } else if (this.ticksUntilBigAgain == 0) { // reset
                this.getAttributes().resetBaseValue(Attributes.SCALE);
                this.ticksUntilBigAgain = -1;
            }
        }
    }

    @Override
    public void spooncraftadditions_setShrinkRayTimer(int ticks) {
        this.ticksUntilBigAgain = ticks;
    }

    @Inject(method = "initInventoryMenu", at = @At("TAIL"))
    private void resetScale(CallbackInfo ci) {
        this.ticksUntilBigAgain = -1;
        this.getAttributes().resetBaseValue(Attributes.SCALE);
    }
}
