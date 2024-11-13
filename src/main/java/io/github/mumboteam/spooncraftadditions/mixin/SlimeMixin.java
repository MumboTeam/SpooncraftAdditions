package io.github.mumboteam.spooncraftadditions.mixin;

import io.github.mumboteam.spooncraftadditions.registry.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class SlimeMixin {
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public final void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (((MobEntity) (Object) this) instanceof SlimeEntity slime && slime.getSize() == 1) {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == Items.BUCKET && !player.handSwinging) {
                slime.discard();
                ItemStack slimeBucket = new ItemStack(ModItems.SLIME_BUCKET);
                if (slime.hasCustomName()) {
                    slimeBucket.set(DataComponentTypes.CUSTOM_NAME, slime.getCustomName());
                }
                if (stack.getCount() == 1) {
                    player.setStackInHand(hand, slimeBucket);
                } else {
                    stack.decrement(1);
                    player.giveItemStack(slimeBucket);
                }
                player.swingHand(hand, true);
                cir.setReturnValue(ActionResult.PASS);
            }
        }
    }
}