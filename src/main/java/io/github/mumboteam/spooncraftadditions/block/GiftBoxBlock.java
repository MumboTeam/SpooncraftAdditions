package io.github.mumboteam.spooncraftadditions.block;

import com.mojang.serialization.MapCodec;
import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import io.github.mumboteam.spooncraftadditions.block.entity.GiftBoxBlockEntity;
import io.github.mumboteam.spooncraftadditions.gui.GiftBoxGui;
import io.github.mumboteam.spooncraftadditions.registry.ModBlockEntityTypes;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class GiftBoxBlock extends BaseEntityBlock implements PolymerHeadBlock {
    public GiftBoxBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(GiftBoxBlock::new);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, @NonNull Level world, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hit) {
        if (player instanceof ServerPlayer serverPlayer) {
            new GiftBoxGui(serverPlayer, pos).open();
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, world, pos, player, hit);
    }

    @Override
    public String getPolymerSkinValue(BlockState state, BlockPos pos, PacketContext context) {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU0ZWJlZmVmYThjYjNjNTg2MGFjODQxMjY1OWUxMjNiYTE1NGQ0YjcwOTZjM2IxMjNjMGQxZmNhNjNjOTc5OSJ9fX0=";
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.ROTATION_16);
    }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.ROTATION_16, Mth.floor(RotationSegment.convertToSegment(ctx.getRotation())));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.PLAYER_HEAD.defaultBlockState().setValue(BlockStateProperties.ROTATION_16, state.getValue(BlockStateProperties.ROTATION_16));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GiftBoxBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        if (world instanceof ServerLevel) {
            return createTickerHelper(type, ModBlockEntityTypes.GIFT_BOX, GiftBoxBlockEntity::tick);
        }
        return null;
    }
}
