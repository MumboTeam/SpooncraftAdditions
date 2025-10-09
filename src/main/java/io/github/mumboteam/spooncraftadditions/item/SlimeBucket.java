package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public class SlimeBucket extends Item implements PolymerItem {
    public SlimeBucket(Settings settings) {
        super(settings.maxCount(1).component(ModComponents.SLIME_EXCITED, false));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.BUCKET;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof ServerPlayerEntity player) {
            boolean excited = Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED));

            if (excited != inSlimeChunk(player)) {
                stack.set(ModComponents.SLIME_EXCITED, !excited);
            }
        }
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        if (stack.getName().getString().toLowerCase().contains("gareth")) {
            if (Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED))) {
                return Identifier.of(SpooncraftAdditions.ID, "gareth_bucket_jumping");
            } else {
                return Identifier.of(SpooncraftAdditions.ID, "gareth_bucket");
            }
        } else {
            if (Boolean.TRUE.equals(stack.get(ModComponents.SLIME_EXCITED))) {
                return Identifier.of(SpooncraftAdditions.ID, "slime_bucket_jumping");
            } else {
                return Identifier.of(SpooncraftAdditions.ID, "slime_bucket");
            }
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, player, RaycastContext.FluidHandling.SOURCE_ONLY);
        Vec3d pos = blockHitResult.getPos();

        if ((blockHitResult.getType() != HitResult.Type.BLOCK || !player.canPlaceOn(blockHitResult.getBlockPos(), blockHitResult.getSide(), stack) || player.handSwinging)) {
            return ActionResult.PASS;
        } else {
            SlimeEntity slime = EntityType.SLIME.create(world, SpawnReason.BUCKET);
            if (slime != null) {
                slime.setSize(1, true);
                slime.setPos(pos.x, pos.y, pos.z);
                slime.setCustomName(stack.get(DataComponentTypes.CUSTOM_NAME));
                world.emitGameEvent(player, GameEvent.ENTITY_PLACE, slime.getEntityPos());
                world.spawnEntity(slime);
            }

            return ActionResult.SUCCESS.withNewHandStack(new ItemStack(Items.BUCKET));
        }

    }

    private boolean inSlimeChunk(ServerPlayerEntity player) {
        ChunkPos chunkPos = player.getChunkPos();
        return ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, player.getEntityWorld().getSeed(), 987234911L).nextInt(10) == 0;
    }
}
