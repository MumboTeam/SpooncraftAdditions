package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import xyz.nucleoid.packettweaker.PacketContext;


import java.util.List;

public class TetrioStick extends Item implements PolymerItem {
    public TetrioStick(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.UNCOMMON).attributeModifiers(createAttributeModifiers()));
    }

    private static AttributeModifiersComponent createAttributeModifiers() {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();

        builder.add(
                EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(
                        Identifier.of("tetrio_stick_attack_damage"),
                        -0.99999,
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
        );

        return builder.build();
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        double strength = 1.5;

        double yaw = Math.toRadians(attacker.getYaw());
        double x = -Math.sin(yaw) * strength;
        double z = Math.cos(yaw) * strength;
        double y = 0.3;

        target.addVelocity(x, y, z);
        if (target instanceof ServerPlayerEntity player) {
            player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(player));
        }
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.tetrio_stick.desc").formatted(Formatting.GRAY));
    }


    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext packetContext) {
        return Items.STICK;
    }
}
