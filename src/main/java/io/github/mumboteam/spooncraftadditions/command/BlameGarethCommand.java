package io.github.mumboteam.spooncraftadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlameGarethCommand {
    private static Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();

    private static String indicator(final int number) {
        final int mod100 = number % 100;

        if (11 <= mod100 && mod100 <= 13) {
            return "th";
        } else {
            return switch (mod100 % 10) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, String command, Boolean blamed) {
        dispatcher.register(Commands.literal(command).executes(context -> {
            Long cooldownEnd = cooldowns.get(context.getSource().getPlayer().getUUID());

            if (cooldownEnd == null || cooldownEnd < System.currentTimeMillis()) {
                BlameGarethState state = BlameGarethState.getServerState(context.getSource().getServer());

                if (blamed) {
                    state.totalBlamed += 1;
                    context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("commands.blamegareth.success",
                            context.getSource().getPlayer().getName(),
                            state.totalBlamed,
                            indicator(state.totalBlamed)).withStyle(ChatFormatting.GOLD), false
                    );
                } else {
                    state.totalForgiven += 1;
                    context.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.translatable("commands.forgivegareth.success",
                            context.getSource().getPlayer().getName(),
                            state.totalForgiven,
                            indicator(state.totalForgiven)).withStyle(ChatFormatting.GOLD), false
                    );
                }
                cooldowns.put(context.getSource().getPlayer().getUUID(), System.currentTimeMillis() + 60*1000);
            } else {
                context.getSource().sendSuccess(() -> Component.translatable("commands.blamegareth.cooldown").withStyle(ChatFormatting.RED), false);
            }

            return 1;
        }));
    }
}