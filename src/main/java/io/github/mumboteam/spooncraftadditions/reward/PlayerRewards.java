package io.github.mumboteam.spooncraftadditions.reward;

import eu.pb4.playerdata.api.PlayerDataApi;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class PlayerRewards {
    ServerPlayerEntity player;
    ClaimedRewards claimedRewards;

    public PlayerRewards(ServerPlayerEntity player) {
        this.player = player;
        this.claimedRewards = PlayerDataApi.getCustomDataFor(player, Rewards.CLAIMED_REWARDS_STORAGE);

        if (this.claimedRewards == null) {
            this.claimedRewards = new ClaimedRewards();
            this.claimedRewards.rewards = new ArrayList<>();
        }
    }

    public Collection<Reward> getClaimable() {
        return Rewards.get().stream()
                .filter(reward -> (!this.claimedRewards.rewards.contains(Rewards.getIdentifier(reward)) && reward.eligiblePlayers().contains(this.player.getUuid())))
                .collect(Collectors.toSet());
    }

    public Collection<Reward> getClaimed() {
        return Rewards.get().stream()
                .filter(reward -> this.claimedRewards.rewards.contains(Rewards.getIdentifier(reward)))
                .collect(Collectors.toSet());
    }

    public Collection<Reward> getAll() {
        return Rewards.get();
    }

    public void claimReward(Reward reward) {
        this.player.giveItemStack(reward.stack());
        this.claimedRewards.rewards.add(Rewards.getIdentifier(reward));

        PlayerDataApi.setCustomDataFor(this.player, Rewards.CLAIMED_REWARDS_STORAGE, this.claimedRewards);
    }
}
