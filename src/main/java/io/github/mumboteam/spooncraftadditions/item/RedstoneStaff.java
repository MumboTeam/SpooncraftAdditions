package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext;
import net.minecraft.util.Hand;

import java.util.List;

public class RedstoneStaff extends Item implements PolymerItem {
    // (type, color, fade color, flicker, trail)
    private static final FireworkExplosionComponent RED_BALL = new FireworkExplosionComponent(FireworkExplosionComponent.Type.LARGE_BALL, new IntArrayList(new int[]{0xFF0000}), new IntArrayList(),false, false);
    private static final int COOLDOWN = 30*20;

    public RedstoneStaff(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!user.getItemCooldownManager().isCoolingDown(itemStack)) {
            user.getItemCooldownManager().set(itemStack, COOLDOWN);
            List<FireworkExplosionComponent> explosions = List.of(RED_BALL);
            FireworksComponent fwComp = new FireworksComponent(0, explosions);
            ItemStack rocketStack = new ItemStack(Items.FIREWORK_ROCKET);
            rocketStack.set(DataComponentTypes.FIREWORKS, fwComp);

            FireworkRocketEntity rocket =
                    new FireworkRocketEntity(
                            world,
                            user.getX(), user.getY() + 1.0, user.getZ(),
                            rocketStack
                    );
            world.spawnEntity(rocket);

            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.redstone_staff.desc").formatted(Formatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.DIAMOND_SWORD;
    }
}
