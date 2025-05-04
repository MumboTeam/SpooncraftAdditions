package io.github.mumboteam.spooncraftadditions.reward;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Uuids;

import java.util.List;
import java.util.UUID;

public record Reward(
        Item item,
        int amount,
        List<UUID> eligiblePlayers
) {
    public static final Codec<Reward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.ITEM.getCodec().fieldOf("item").forGetter(Reward::item),
            Codec.INT.optionalFieldOf("amount", 1).forGetter(Reward::amount),
            Codec.list(Uuids.CODEC).fieldOf("eligiblePlayers").forGetter(Reward::eligiblePlayers)
    ).apply(instance, Reward::new));

    @Override
    public Item item() {
        return item;
    }

    @Override
    public List<UUID> eligiblePlayers() {
        return eligiblePlayers;
    }
}
