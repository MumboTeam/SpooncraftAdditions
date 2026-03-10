package io.github.mumboteam.spooncraftadditions.gui;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.layered.Layer;
import eu.pb4.sgui.api.gui.layered.LayerView;
import eu.pb4.sgui.api.gui.layered.LayeredGui;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.block.entity.GiftBoxBlockEntity;
import io.github.mumboteam.spooncraftadditions.reward.PlayerRewards;
import io.github.mumboteam.spooncraftadditions.reward.Reward;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;

import java.util.List;

public class GiftBoxGui extends LayeredGui {
    FontDescription.Resource GUI_FONT = new FontDescription.Resource(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "gui"));

    private final BlockPos pos;
    private final ServerPlayer player;
    LayerView open;

    Layer rewardsTab;
    Layer claimedTab;
    Layer allTab;

    String rewardsTabTitle;
    String claimedTabTitle;
    String allTabTitle;

    PlayerRewards playerRewards;

    public GiftBoxGui(ServerPlayer player, BlockPos pos) {
        super(MenuType.GENERIC_9x6, player, false);

        this.playerRewards = new PlayerRewards(player);
        this.player = player;
        this.pos = pos;

        Layer tabs = new Layer(1, 9);

        this.rewardsTab = new Layer(5, 9);
        this.claimedTab = new Layer(5, 9);
        this.allTab = new Layer(5, 9);

        for (int i=0; i<3; i++) {
            tabs.setSlot(i, new GuiElementBuilder(ItemStack.EMPTY).setCallback(() -> switchTab(this.rewardsTab, this.rewardsTabTitle)));
        }

        for (int i=3; i<6; i++) {
            tabs.setSlot(i, new GuiElementBuilder(ItemStack.EMPTY).setCallback(() -> switchTab(this.claimedTab, this.claimedTabTitle)));
        }

        for (int i=6; i<9; i++) {
            tabs.setSlot(i, new GuiElementBuilder(ItemStack.EMPTY).setCallback(() -> switchTab(this.allTab, this.allTabTitle)));
        }

        this.addLayer(tabs, 0, 0).setZIndex(5);

        refreshPages();
        switchTab(this.rewardsTab, this.rewardsTabTitle);
    }

    private void claimReward(Reward reward) {
        this.playerRewards.claimReward(reward);
        this.player.connection.send(new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, this.player.getX(), this.player.getY(), this.player.getZ(), 1, 1, this.player.getRandom().nextLong()));

        FireworkExplosion explosion = new FireworkExplosion(FireworkExplosion.Shape.LARGE_BALL, IntList.of(0xea625e), IntList.of(0x4cb679), true, true);
        ItemStack rocket = Items.FIREWORK_ROCKET.getDefaultInstance();
        rocket.set(DataComponents.FIREWORKS, new Fireworks(1, List.of(explosion)));

        FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(this.player.level(),
                this.pos.getX() + 0.5,
                this.pos.getY() + 0.5,
                this.pos.getZ() + 0.5,
                rocket
        );
        this.player.level().addFreshEntity(fireworkRocketEntity);

        refreshPages();
        this.setTitle(Component.empty().append(Component.literal("-1." + this.rewardsTabTitle).setStyle(Style.EMPTY.withColor(0xFFFFFF).withFont(GUI_FONT))));

        if (this.player.level().getBlockEntity(this.pos) instanceof GiftBoxBlockEntity blockEntity) {
            blockEntity.clearCount(this.player.connection);
        }
    }

    private void refreshPages() {
        this.rewardsTab.clearSlots();
        this.claimedTab.clearSlots();
        this.allTab.clearSlots();

        int pos = 0;
        for (Reward reward : playerRewards.getClaimable()) {
            rewardsTab.setSlot(pos, new GuiElementBuilder(reward.stack())
                    .addLoreLine(Component.empty())
                    .addLoreLine(Component.translatable("gui.spooncraftadditions.giftbox.claim").setStyle(Style.EMPTY.withItalic(false).withColor(ChatFormatting.GREEN)))
                    .setCallback(() -> claimReward(reward)));
            pos += 1;
        }
        this.rewardsTabTitle = "-2.";
        if (pos == 0) {
            this.rewardsTabTitle += "-5.";
        }

        pos = 0;
        for (Reward reward : playerRewards.getClaimed()) {
            claimedTab.setSlot(pos, new GuiElementBuilder(reward.stack()));
            pos += 1;
        }
        this.claimedTabTitle = "-3.";
        if (pos == 0) {
            this.claimedTabTitle += "-6.";
        }

        pos = 0;
        for (Reward reward : playerRewards.getAll()) {
            allTab.setSlot(pos, new GuiElementBuilder(reward.stack()));
            pos += 1;
        }
        this.allTabTitle = "-4.";
    }

    private void switchTab(Layer tab, String title) {
        if (this.open != null) {
            if (this.open.getLayer() == tab) {
                return;
            }
            this.removeLayer(this.open);
        }
        this.open = this.addLayer(tab, 0, 1);
        this.setTitle(Component.empty().append(Component.literal("-1." + title).setStyle(Style.EMPTY.withColor(0xFFFFFF).withFont(GUI_FONT))));
    }
}
