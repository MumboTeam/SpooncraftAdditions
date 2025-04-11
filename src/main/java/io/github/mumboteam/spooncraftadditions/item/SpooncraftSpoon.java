package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class SpooncraftSpoon extends ShovelItem implements PolymerItem {
    public SpooncraftSpoon(Settings settings) {
        super(SpooncraftMaterial.MATERIAL, 1.5F, -3.0F, settings.fireproof().rarity(Rarity.EPIC));
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.add(0, Text.translatable("item.spooncraftadditions.spooncraft_spoon.desc[0]").formatted(Formatting.RED));
        tooltip.add(1, Text.translatable("item.spooncraftadditions.spooncraft_spoon.desc[1]").formatted(Formatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.NETHERITE_SHOVEL;
    }

    @Override
    public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {
        if (!isUsable(user.getMainHandStack())) {
            return false;
        }

        return super.canMine(stack, state, world, pos, user);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (isUsable(context.getStack())) {
            return super.useOnBlock(context);
        }

        return ActionResult.FAIL;
    }
}
