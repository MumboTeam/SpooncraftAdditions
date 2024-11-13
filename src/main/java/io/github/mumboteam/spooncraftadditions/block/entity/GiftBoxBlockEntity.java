package io.github.mumboteam.spooncraftadditions.block.entity;

import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment;
import eu.pb4.polymer.virtualentity.api.elements.TextDisplayElement;
import eu.pb4.polymer.virtualentity.api.tracker.DisplayTrackedData;
import io.github.mumboteam.spooncraftadditions.registry.ModBlockEntityTypes;
import io.github.mumboteam.spooncraftadditions.reward.PlayerRewards;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GiftBoxBlockEntity extends BlockEntity {
    private final TextDisplayElement textDisplay = new TextDisplayElement();
    private ElementHolder elementHolder;
    private final Map<ServerPlayNetworkHandler, Integer> claimableRewardCount = new HashMap<>();
    private final Text title = Text.translatable("block.spooncraftadditions.gift_box.title").formatted(Formatting.BOLD).formatted(Formatting.GOLD);

    public GiftBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.GIFT_BOX, pos, state);
        this.textDisplay.setText(title);
        this.textDisplay.setBillboardMode(DisplayEntity.BillboardMode.CENTER);
        this.textDisplay.setScale(new Vector3f(1.5f, 1.5f, 0.01f));
        this.textDisplay.setBackground(0x00000000);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, GiftBoxBlockEntity blockEntity) {
        blockEntity.tick();
    }

    public void tick() {
        if (elementHolder == null) {
            Vec3d pos = Vec3d.ofCenter(this.getPos()).add(0, 0.5, 0);
            elementHolder = new ElementHolder();

            ChunkAttachment.of(elementHolder, (ServerWorld) world, pos);
        }

        elementHolder.addElement(this.textDisplay);
        elementHolder.tick();
        for (ServerPlayNetworkHandler player : Objects.requireNonNull(this.elementHolder.getWatchingPlayers())) {
            if (!claimableRewardCount.containsKey(player)) {
                claimableRewardCount.put(player, new PlayerRewards(player.getPlayer()).getClaimable().size());
            }
            int count = claimableRewardCount.get(player);
            player.sendPacket(new EntityTrackerUpdateS2CPacket(
                    textDisplay.getEntityId(),
                    List.of(DataTracker.SerializedEntry.of(DisplayTrackedData.Text.TEXT, Text.empty()
                            .append(title)
                            .append(count == 1 ?
                                    Text.translatable("block.spooncraftadditions.gift_box.reward") :
                                    Text.translatable("block.spooncraftadditions.gift_box.rewards", count)
                            )
                    ))
            ));
        }
    }

    @Override
    public void markRemoved() {
        super.markRemoved();
        if (this.elementHolder != null) {
            this.elementHolder.destroy();
            this.elementHolder = null;
            this.textDisplay.setHolder(null);
        }
    }

    public void clearCount(ServerPlayNetworkHandler player) {
        claimableRewardCount.remove(player);
    }
}
