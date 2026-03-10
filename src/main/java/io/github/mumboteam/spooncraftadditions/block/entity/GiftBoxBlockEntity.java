package io.github.mumboteam.spooncraftadditions.block.entity;

import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment;
import eu.pb4.polymer.virtualentity.api.elements.TextDisplayElement;
import eu.pb4.polymer.virtualentity.api.tracker.DisplayTrackedData;
import io.github.mumboteam.spooncraftadditions.registry.ModBlockEntityTypes;
import io.github.mumboteam.spooncraftadditions.reward.PlayerRewards;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Display;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GiftBoxBlockEntity extends BlockEntity {
    private final TextDisplayElement textDisplay = new TextDisplayElement();
    private ElementHolder elementHolder;
    private final Map<ServerGamePacketListenerImpl, Integer> claimableRewardCount = new HashMap<>();
    private final Component title = Component.translatable("block.spooncraftadditions.gift_box.title").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD);

    public GiftBoxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.GIFT_BOX, pos, state);
        this.textDisplay.setText(title);
        this.textDisplay.setBillboardMode(Display.BillboardConstraints.CENTER);
        this.textDisplay.setScale(new Vector3f(1.5f, 1.5f, 0.01f));
        this.textDisplay.setBackground(0x00000000);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, GiftBoxBlockEntity blockEntity) {
        blockEntity.tick();
    }

    public void tick() {
        if (elementHolder == null) {
            Vec3 pos = Vec3.atCenterOf(this.getBlockPos()).add(0, 0.5, 0);
            elementHolder = new ElementHolder();

            ChunkAttachment.of(elementHolder, (ServerLevel) level, pos);
        }

        elementHolder.addElement(this.textDisplay);
        elementHolder.tick();
        for (ServerGamePacketListenerImpl player : Objects.requireNonNull(this.elementHolder.getWatchingPlayers())) {
            if (!claimableRewardCount.containsKey(player)) {
                claimableRewardCount.put(player, new PlayerRewards(player.getPlayer()).getClaimable().size());
            }
            int count = claimableRewardCount.get(player);
            player.send(new ClientboundSetEntityDataPacket(
                    textDisplay.getEntityId(),
                    List.of(SynchedEntityData.DataValue.create(DisplayTrackedData.Text.TEXT, Component.empty()
                            .append(title)
                            .append(count == 1 ?
                                    Component.translatable("block.spooncraftadditions.gift_box.reward") :
                                    Component.translatable("block.spooncraftadditions.gift_box.rewards", count)
                            )
                    ))
            ));
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (this.elementHolder != null) {
            this.elementHolder.destroy();
            this.elementHolder = null;
            this.textDisplay.setHolder(null);
        }
    }

    public void clearCount(ServerGamePacketListenerImpl player) {
        claimableRewardCount.remove(player);
    }
}
