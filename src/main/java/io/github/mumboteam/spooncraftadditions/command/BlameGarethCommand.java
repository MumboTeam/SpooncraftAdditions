package io.github.mumboteam.spooncraftadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, String command, Boolean blamed) {
        dispatcher.register(CommandManager.literal(command).executes(context -> {
            Long cooldownEnd = cooldowns.get(context.getSource().getPlayer().getUuid());

            if (cooldownEnd == null || cooldownEnd < System.currentTimeMillis()) {
                BlameGarethState state = BlameGarethState.getServerState(context.getSource().getServer());

                if (blamed) {
                    state.totalBlamed += 1;
                    context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("commands.blamegareth.success",
                            context.getSource().getPlayer().getName(),
                            state.totalBlamed,
                            indicator(state.totalBlamed)).formatted(Formatting.GOLD), false
                    );
                } else {
                    state.totalForgiven += 1;
                    context.getSource().getServer().getPlayerManager().broadcast(Text.translatable("commands.forgivegareth.success",
                            context.getSource().getPlayer().getName(),
                            state.totalForgiven,
                            indicator(state.totalForgiven)).formatted(Formatting.GOLD), false
                    );
                }
                cooldowns.put(context.getSource().getPlayer().getUuid(), System.currentTimeMillis() + 60*1000);
            } else {
                context.getSource().sendFeedback(() -> Text.translatable("commands.blamegareth.cooldown").formatted(Formatting.RED), false);
            }

            return 1;
        }));
    }
}