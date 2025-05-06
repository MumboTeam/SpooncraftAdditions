package io.github.mumboteam.spooncraftadditions.reward;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Uuids;

import java.util.List;
import java.util.UUID;

public record Reward(
        ItemStack stack,
        List<UUID> eligiblePlayers
) {
    public static final Codec<Reward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            // vanilla Item codec
            ItemStack.CODEC.fieldOf("itemstack").forGetter(Reward::stack),
            Codec.list(Uuids.CODEC).fieldOf("eligiblePlayers").forGetter(Reward::eligiblePlayers)
    ).apply(instance, Reward::new));

    @Override
    public ItemStack stack() {
        return stack;
    }

    @Override
    public List<UUID> eligiblePlayers() {
        return eligiblePlayers;
    }
}
