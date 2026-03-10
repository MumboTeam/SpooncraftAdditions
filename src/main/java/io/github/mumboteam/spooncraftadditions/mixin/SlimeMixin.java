package io.github.mumboteam.spooncraftadditions.mixin;

import io.github.mumboteam.spooncraftadditions.registry.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class SlimeMixin {
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public final void interact(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (((Mob) (Object) this) instanceof Slime slime && slime.getSize() == 1) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem() == Items.BUCKET && !player.swinging) {
                slime.discard();
                ItemStack slimeBucket = new ItemStack(ModItems.SLIME_BUCKET);
                if (slime.hasCustomName()) {
                    slimeBucket.set(DataComponents.CUSTOM_NAME, slime.getCustomName());
                }
                if (stack.getCount() == 1) {
                    player.setItemInHand(hand, slimeBucket);
                } else {
                    stack.shrink(1);
                    player.addItem(slimeBucket);
                }
                player.swing(hand, true);
                cir.setReturnValue(InteractionResult.PASS);
            }
        }
    }
}