package io.github.mumboteam.spooncraftadditions.reward;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record Reward(
        Item item,
        int amount,
        Map<String, JsonElement> components,
        List<UUID> eligiblePlayers
) {
    public static final Codec<Reward> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            // vanilla Item codec
            Registries.ITEM.getCodec().fieldOf("item").forGetter(Reward::item),              // :contentReference[oaicite:1]{index=1}
            Codec.INT.optionalFieldOf("amount", 1).forGetter(Reward::amount),                // :contentReference[oaicite:2]{index=2}
            // arbitrary JSON-object map
            Codec.unboundedMap(Codec.STRING, net.minecraft.util.dynamic.Codecs.JSON_ELEMENT)
                    .optionalFieldOf("components", Map.of()).forGetter(Reward::components),      // :contentReference[oaicite:3]{index=3}
            Codec.list(Uuids.CODEC).fieldOf("eligiblePlayers").forGetter(Reward::eligiblePlayers)
    ).apply(instance, Reward::new));

    public ItemStack stack() {
        ItemStack stack = new ItemStack(item, amount);
        applyBundleContents(stack, components);
        return stack;
    }

    public void applyBundleContents(ItemStack stack, Map<String, JsonElement> components) {
        JsonElement raw = components.get("bundle_contents");
        if (raw == null || !raw.isJsonArray()) return;                          // nothing to do

        JsonArray array = raw.getAsJsonArray();
        List<ItemStack> toBundle = new ArrayList<>();
        for (JsonElement e : array) {
            if (!e.isJsonObject()) continue;
            JsonObject obj = e.getAsJsonObject();

            String id = obj.get("id").getAsString();
            int count = obj.get("count").getAsInt();

            ItemStack entry = new ItemStack(Registries.ITEM.get(Identifier.of(id)), count);
            toBundle.add(entry);
        }

        BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(stack.get(DataComponentTypes.BUNDLE_CONTENTS));
        builder.clear();
        for (ItemStack sub : toBundle) {builder.add(sub);}
        stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());

    }

    @Override
    public Item item() {
        return item;
    }

    @Override
    public List<UUID> eligiblePlayers() {
        return eligiblePlayers;
    }
}
