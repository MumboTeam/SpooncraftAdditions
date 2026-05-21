package io.github.mumboteam.spooncraftadditions.command;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.storage.SavedDataStorage;

import java.util.Objects;

public class BlameGarethState extends SavedData {
    public Integer totalBlamed = 0;
    public Integer totalForgiven = 0;

    public CompoundTag writeNbt(CompoundTag nbt) {
        nbt.putInt("totalBlamed", totalBlamed);
        nbt.putInt("totalForgiven", totalForgiven);
        return nbt;
    }

    public static BlameGarethState createFromNbt(CompoundTag tag) {
        BlameGarethState state = new BlameGarethState();
        state.totalBlamed = tag.getInt("totalBlamed").orElse(0);
        state.totalForgiven = tag.getInt("totalForgiven").orElse(0);
        return state;
    }

    private static final SavedDataType<BlameGarethState> STATE_TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "blamegareth"),
            BlameGarethState::new,
            CompoundTag.CODEC.xmap(
                    BlameGarethState::createFromNbt,
                    state -> state.writeNbt(new CompoundTag())
            ),
            null
    );

    public static BlameGarethState getServerState(MinecraftServer server) {
        SavedDataStorage persistentStateManager = Objects.requireNonNull(server.getLevel(Level.OVERWORLD)).getDataStorage();
        BlameGarethState state = persistentStateManager.computeIfAbsent(STATE_TYPE);
        state.setDirty();

        return state;
    }
}
