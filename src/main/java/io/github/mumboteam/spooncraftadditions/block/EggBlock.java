package io.github.mumboteam.spooncraftadditions.block;

import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.Block;
import eu.pb4.factorytools.api.block.FactoryBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.decoration.Brightness;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public class EggBlock extends Block implements FactoryBlock {
    public EggBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState blockState, PacketContext packetContext) {
        return Blocks.BARRIER.getDefaultState();
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.WHITE_WOOL.getDefaultState();
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        ElementHolder holder = new ElementHolder();
        ItemDisplayElement model = ItemDisplayElementUtil.createSimple(initialBlockState.getBlock().asItem());
        model.setDisplaySize(1, 1);
        model.setBrightness(new Brightness(15, 15));
        model.setItemDisplayContext(ItemDisplayContext.NONE);
        holder.addElement(model);
        return holder;
    }
}