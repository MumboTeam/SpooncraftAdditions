package io.github.mumboteam.spooncraftadditions.mixin;

import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import io.github.mumboteam.spooncraftadditions.item.DuckHat;
import io.github.mumboteam.spooncraftadditions.item.Hat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(
            method = "onDurabilityChange",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onDurabilityChangeHook(int damage, @Nullable ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;

        if (stack.getItem() instanceof Hat || stack.getItem() instanceof DuckHat) {
            if (damage >= stack.getMaxDamage()) {
                // If the item isn't disabled, then it just broke
                if (!stack.getOrDefault(ModComponents.DISABLED, false) && player != null) {
                    player.sendMessage(Text.of("Your hat is broken!"), true);
                }
                stack.setDamage(stack.getMaxDamage());
                ci.cancel();
            }
        }
    }

    @Inject(method = "setDamage", at = @At("HEAD"))
    private void onSetDamage(int damage, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.getItem() instanceof Hat || stack.getItem() instanceof DuckHat) {
            boolean broken = damage >= stack.getMaxDamage();
            boolean currentlyDisabled = stack.getOrDefault(ModComponents.DISABLED, false);

            if (broken && !currentlyDisabled) {
                stack.set(ModComponents.DISABLED, true);
            } else if (!broken && currentlyDisabled) {
                stack.set(ModComponents.DISABLED, false);
            }
        }
    }




}
