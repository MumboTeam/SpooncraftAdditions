package io.github.mumboteam.spooncraftadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class IsItGarethsFaultCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("isitgarethsfault").executes(context -> {
            BlameGarethState state = BlameGarethState.getServerState(context.getSource().getServer());

            context.getSource().sendFeedback(() -> Text.translatable("commands.isitgarethsfault.success",
                    state.totalBlamed,
                    (state.totalBlamed == 1) ? "" : "s",
                    state.totalForgiven,
                    (state.totalForgiven == 1) ? "" : "s",
                    (state.totalBlamed > state.totalForgiven ) ? Text.translatable("gui.yes").formatted(Formatting.GREEN) : Text.translatable("gui.no").formatted(Formatting.RED)
            ).formatted(Formatting.GOLD), false);

            return 1;
        }));
    }
}
