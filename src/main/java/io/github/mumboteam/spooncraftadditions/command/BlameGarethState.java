package io.github.mumboteam.spooncraftadditions.command;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.Objects;

public class BlameGarethState extends PersistentState {
    public Integer totalBlamed = 0;
    public Integer totalForgiven = 0;

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("totalBlamed", totalBlamed);
        nbt.putInt("totalForgiven", totalForgiven);
        return nbt;
    }

    public static BlameGarethState createFromNbt (NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        BlameGarethState state = new BlameGarethState();
        state.totalBlamed = tag.getInt("totalBlamed");
        state.totalForgiven = tag.getInt("totalForgiven");
        return state;
    }

    private static Type<BlameGarethState> type = new Type<>(
            BlameGarethState::new,
            BlameGarethState::createFromNbt,
            null
    );

    public static BlameGarethState getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager();
        BlameGarethState state = persistentStateManager.getOrCreate(type, SpooncraftAdditions.ID + "_blamegareth");
        state.markDirty();

        return state;
    }
}
