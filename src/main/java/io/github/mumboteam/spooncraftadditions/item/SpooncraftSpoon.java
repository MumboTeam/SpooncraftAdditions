package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpooncraftSpoon extends ShovelItem implements PolymerItem {
    private final PolymerModelData cmd;

    public SpooncraftSpoon(Settings settings) {
        super(SpooncraftMaterial.INSTANCE, settings.fireproof().rarity(Rarity.EPIC).attributeModifiers(ShovelItem.createAttributeModifiers(SpooncraftMaterial.INSTANCE, 1.5F, -3.0F)));
        this.cmd = PolymerResourcePackUtils.requestModel(Items.NETHERITE_SHOVEL, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_spoon"));
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, @Nullable ServerPlayerEntity player) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.spooncraft_spoon.desc").formatted(Formatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.NETHERITE_SHOVEL;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.cmd.value();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        if (!isUsable(miner.getMainHandStack())) {
            return false;
        }

        return super.canMine(state, world, pos, miner);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (isUsable(context.getStack())) {
            return super.useOnBlock(context);
        }

        return ActionResult.FAIL;
    }
}
