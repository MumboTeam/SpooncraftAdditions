package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.accessor.ShrinkRayTimerAccess;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ARGB;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import org.jspecify.annotations.NonNull;

public class ShrinkRay extends Item implements PolymerItem {
    public static int DURATION_TICKS = 20*60*2;
    public static int SHRINK_TICKS = 20;
    public static int GROW_TICKS = 20*30;
    public static double SIZE = 0.1;

    public ShrinkRay(Properties settings) {
        super(settings.stacksTo(1));
    }

    @Override
    public @NonNull InteractionResult interactLivingEntity(@NonNull ItemStack stack, Player user, @NonNull LivingEntity entity, @NonNull InteractionHand hand) {
        if (!user.getCooldowns().isOnCooldown(stack) && entity instanceof ServerPlayer player) {
            ((ShrinkRayTimerAccess)player).spooncraftadditions_setShrinkRayTimer(DURATION_TICKS);
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, SHRINK_TICKS, 1, false, false));
            user.getCooldowns().addCooldown(stack, DURATION_TICKS);
            user.makeSound(SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(SpooncraftAdditions.ID, "item.shrink_ray")));

            ParticleOptions effect = new DustParticleOptions(ARGB.colorFromFloat(1, 0, 0.5f, 1), 2.0f);
            Vec3 pos = user.position().add(0, 1.3, 0);
            Vec3 targetPos = player.position();

            while (!pos.closerThan(targetPos, 0.5)) {
                pos = pos.lerp(targetPos, 0.1);
                player.level().sendParticles(effect, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.DIAMOND_HORSE_ARMOR;
    }
}
