package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import org.jspecify.annotations.NonNull;

public class SlimeBucket extends Item implements PolymerItem {
    public SlimeBucket(Properties settings) {
        super(settings.stacksTo(1).component(ModComponents.SLIME_EXCITED, false));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.BUCKET;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel world, @NonNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof ServerPlayer player) {
            boolean excited = Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED));

            if (excited != inSlimeChunk(player)) {
                stack.set(ModComponents.SLIME_EXCITED, !excited);
            }
        }
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context, HolderLookup.Provider lookup) {
        if (stack.getHoverName().getString().toLowerCase().contains("gareth")) {
            if (Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED))) {
                return Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "gareth_bucket_jumping");
            } else {
                return Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "gareth_bucket");
            }
        } else {
            if (Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED))) {
                return Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "slime_bucket_jumping");
            } else {
                return Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "slime_bucket");
            }
        }
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level world, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
        Vec3 pos = blockHitResult.getLocation();

        if ((blockHitResult.getType() != HitResult.Type.BLOCK || !player.mayUseItemAt(blockHitResult.getBlockPos(), blockHitResult.getDirection(), stack) || player.swinging)) {
            return InteractionResult.PASS;
        } else {
            Slime slime = EntityType.SLIME.create(world, EntitySpawnReason.BUCKET);
            if (slime != null) {
                slime.setSize(1, true);
                slime.setPosRaw(pos.x, pos.y, pos.z);
                slime.setCustomName(stack.get(DataComponents.CUSTOM_NAME));
                world.gameEvent(player, GameEvent.ENTITY_PLACE, slime.position());
                world.addFreshEntity(slime);
            }

            return InteractionResult.SUCCESS.heldItemTransformedTo(new ItemStack(Items.BUCKET));
        }

    }

    private boolean inSlimeChunk(ServerPlayer player) {
        ChunkPos chunkPos = player.chunkPosition();
        return WorldgenRandom.seedSlimeChunk(chunkPos.x(), chunkPos.z(), player.level().getSeed(), 987234911L).nextInt(10) == 0;
    }
}
