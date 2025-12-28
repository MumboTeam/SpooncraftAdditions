package io.github.mumboteam.spooncraftadditions.command;

import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import net.minecraft.world.World;

import java.util.Objects;

public class BlameGarethState extends PersistentState {
    public Integer totalBlamed = 0;
    public Integer totalForgiven = 0;

    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("totalBlamed", totalBlamed);
        nbt.putInt("totalForgiven", totalForgiven);
        return nbt;
    }

    public static BlameGarethState createFromNbt(NbtCompound tag) {
        BlameGarethState state = new BlameGarethState();
        state.totalBlamed = tag.getInt("totalBlamed").orElse(0);
        state.totalForgiven = tag.getInt("totalForgiven").orElse(0);
        return state;
    }

    private static final PersistentStateType<BlameGarethState> STATE_TYPE = new PersistentStateType<>(
            SpooncraftAdditions.ID + "_blamegareth",
            BlameGarethState::new,
            NbtCompound.CODEC.xmap(
                    BlameGarethState::createFromNbt,
                    state -> state.writeNbt(new NbtCompound())
            ),
            null
    );

    public static BlameGarethState getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager();
        BlameGarethState state = persistentStateManager.getOrCreate(STATE_TYPE);
        state.markDirty();

        return state;
    }
}
