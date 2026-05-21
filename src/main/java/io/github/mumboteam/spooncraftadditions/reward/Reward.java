package io.github.mumboteam.spooncraftadditions.reward;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;
import java.util.UUID;

public record Reward(
        ItemStackTemplate stack,
        List<UUID> eligiblePlayers
) {
    public static final Codec<Reward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStackTemplate.CODEC.fieldOf("stack").forGetter(Reward::stack),
            Codec.list(UUIDUtil.AUTHLIB_CODEC).fieldOf("eligiblePlayers").forGetter(Reward::eligiblePlayers)
    ).apply(instance, Reward::new));

    @Override
    public ItemStackTemplate stack() {
        return stack;
    }

    @Override
    public List<UUID> eligiblePlayers() {
        return eligiblePlayers;
    }
}
