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
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class GiftBoxGui extends LayeredGui {
    private final BlockPos pos;
    private final ServerPlayerEntity player;
    LayerView open;

    Layer rewardsTab;
    Layer claimedTab;
    Layer allTab;

    String rewardsTabTitle;
    String claimedTabTitle;
    String allTabTitle;

    PlayerRewards playerRewards;

    public GiftBoxGui(ServerPlayerEntity player, BlockPos pos) {
        super(ScreenHandlerType.GENERIC_9X6, player, false);

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
        this.getPlayer().playSoundToPlayer(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), SoundCategory.MASTER, 1, 1);

        FireworkExplosionComponent explosion = new FireworkExplosionComponent(FireworkExplosionComponent.Type.LARGE_BALL, IntList.of(0xea625e), IntList.of(0x4cb679), true, true);
        ItemStack rocket = Items.FIREWORK_ROCKET.getDefaultStack();
        rocket.set(DataComponentTypes.FIREWORKS, new FireworksComponent(1, List.of(explosion)));

        FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(this.player.getServerWorld(),
                this.pos.getX() + 0.5,
                this.pos.getY() + 0.5,
                this.pos.getZ() + 0.5,
                rocket
        );
        this.player.getServerWorld().spawnEntity(fireworkRocketEntity);

        refreshPages();
        this.setTitle(Text.empty().append(Text.literal("-1." + this.rewardsTabTitle).setStyle(Style.EMPTY.withColor(0xFFFFFF).withFont(Identifier.of(SpooncraftAdditions.ID, "gui")))));

        if (this.getPlayer().getServerWorld().getBlockEntity(this.pos) instanceof GiftBoxBlockEntity blockEntity) {
            blockEntity.clearCount(this.getPlayer().networkHandler);
        }
    }

    private void refreshPages() {
        this.rewardsTab.clearSlots();
        this.claimedTab.clearSlots();
        this.allTab.clearSlots();

        int pos = 0;
        for (Reward reward : playerRewards.getClaimable()) {
            rewardsTab.setSlot(pos, new GuiElementBuilder(reward.item().getDefaultStack())
                    .addLoreLine(Text.empty())
                    .addLoreLine(Text.translatable("gui.spooncraftadditions.giftbox.claim").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.GREEN)))
                    .setCallback(() -> claimReward(reward)));
            pos += 1;
        }
        this.rewardsTabTitle = "-2.";
        if (pos == 0) {
            this.rewardsTabTitle += "-5.";
        }

        pos = 0;
        for (Reward reward : playerRewards.getClaimed()) {
            claimedTab.setSlot(pos, new GuiElementBuilder(reward.item().getDefaultStack()));
            pos += 1;
        }
        this.claimedTabTitle = "-3.";
        if (pos == 0) {
            this.claimedTabTitle += "-6.";
        }

        pos = 0;
        for (Reward reward : playerRewards.getAll()) {
            allTab.setSlot(pos, new GuiElementBuilder(reward.item().getDefaultStack()));
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
        this.setTitle(Text.empty().append(Text.literal("-1." + title).setStyle(Style.EMPTY.withColor(0xFFFFFF).withFont(Identifier.of(SpooncraftAdditions.ID, "gui")))));
        this.getPlayer().playSoundToPlayer(SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.MASTER, 0.8f, 1);
    }
}
