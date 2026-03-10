package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class RedstoneStaff extends Item implements PolymerItem {
    // (type, color, fade color, flicker, trail)
    private static final FireworkExplosion RED_BALL = new FireworkExplosion(FireworkExplosion.Shape.LARGE_BALL, new IntArrayList(new int[]{0xFF0000}), new IntArrayList(),false, false);
    private static final int COOLDOWN = 30*20;

    public RedstoneStaff(Properties settings) {
        super(settings.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player user = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        Level world = context.getLevel();

        if (user != null && !user.getCooldowns().isOnCooldown(itemStack)) {
            user.getCooldowns().addCooldown(itemStack, COOLDOWN);
            List<FireworkExplosion> explosions = List.of(RED_BALL);
            Fireworks fwComp = new Fireworks(0, explosions);
            ItemStack rocketStack = new ItemStack(Items.FIREWORK_ROCKET);
            rocketStack.set(DataComponents.FIREWORKS, fwComp);

            Vec3 pos = context.getClickLocation();
            FireworkRocketEntity rocket =
                    new FireworkRocketEntity(
                            world,
                            pos.x(), pos.y(), pos.z(),
                            rocketStack
                    );
            world.addFreshEntity(rocket);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions.redstone_staff.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.DIAMOND_SWORD;
    }
}
