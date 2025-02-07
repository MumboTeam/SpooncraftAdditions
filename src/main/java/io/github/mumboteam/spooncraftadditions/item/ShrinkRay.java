package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import io.github.mumboteam.spooncraftadditions.accessor.ShrinkRayTimerAccess;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import xyz.nucleoid.packettweaker.PacketContext;

public class ShrinkRay extends Item implements PolymerItem {
    public static int DURATION_TICKS = 20*60*2;
    public static int SHRINK_TICKS = 20;
    public static int GROW_TICKS = 20*30;
    public static double SIZE = 0.1;

    public ShrinkRay(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(stack) && entity instanceof ServerPlayerEntity player) {
            ((ShrinkRayTimerAccess)player).spooncraftadditions_setShrinkRayTimer(DURATION_TICKS);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, SHRINK_TICKS, 1, false, false));
            user.getItemCooldownManager().set(stack, DURATION_TICKS);
            user.playSound(SoundEvent.of(Identifier.of(SpooncraftAdditions.ID, "item.shrink_ray")));
            user.playSoundToPlayer(SoundEvent.of(Identifier.of(SpooncraftAdditions.ID, "item.shrink_ray")), SoundCategory.PLAYERS, 1.0f, 1.0f);

            ParticleEffect effect = new DustParticleEffect(ColorHelper.fromFloats(1, 0, 0.5f, 1), 2.0f);
            Vec3d pos = user.getPos().add(0, 1.3, 0);
            Vec3d targetPos = player.getPos();

            while (!pos.isInRange(targetPos, 0.5)) {
                pos = pos.lerp(targetPos, 0.1);
                player.getServerWorld().spawnParticles(effect, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            }

            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.DIAMOND_HORSE_ARMOR;
    }
}
