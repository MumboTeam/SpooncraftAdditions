package io.github.mumboteam.spooncraftadditions.reward;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import eu.pb4.playerdata.api.storage.JsonDataStorage;
import eu.pb4.playerdata.api.storage.PlayerDataStorage;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.registry.TinyRegistry;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

public class Rewards {
    private static final TinyRegistry<Reward> REWARDS = TinyRegistry.create();
    public static final PlayerDataStorage<ClaimedRewards> CLAIMED_REWARDS_STORAGE = new JsonDataStorage<>(SpooncraftAdditions.ID + "_claimed_rewards", ClaimedRewards.class);

    public static void reload(ResourceManager manager) {
        REWARDS.clear();

        for(Map.Entry<Identifier, Resource> resource : manager.findResources("reward", path -> path.toString().endsWith(".json")).entrySet()) {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getValue().getInputStream()))) {
                JsonElement json = JsonParser.parseReader(reader);
                DataResult<Reward> result = Reward.CODEC.parse(JsonOps.INSTANCE, json);
                REWARDS.register(resource.getKey(), result.getOrThrow());

            } catch(Exception e) {
                SpooncraftAdditions.LOGGER.error("Error occurred while loading resource json {}", resource.getKey().toString(), e);
            }
        }
    }

    public static Collection<Reward> get() {
        return REWARDS.values();
    }

    public static Identifier getIdentifier(Reward reward) {
        return REWARDS.getIdentifier(reward);
    }
}
