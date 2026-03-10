package io.github.mumboteam.spooncraftadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class IsItGarethsFaultCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("isitgarethsfault").executes(context -> {
            BlameGarethState state = BlameGarethState.getServerState(context.getSource().getServer());

            context.getSource().sendSuccess(() -> Component.translatable("commands.isitgarethsfault.success",
                    state.totalBlamed,
                    (state.totalBlamed == 1) ? "" : "s",
                    state.totalForgiven,
                    (state.totalForgiven == 1) ? "" : "s",
                    (state.totalBlamed > state.totalForgiven ) ? Component.translatable("gui.yes").withStyle(ChatFormatting.GREEN) : Component.translatable("gui.no").withStyle(ChatFormatting.RED)
            ).withStyle(ChatFormatting.GOLD), false);

            return 1;
        }));
    }
}
