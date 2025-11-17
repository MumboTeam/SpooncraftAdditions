package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityAttachments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent.Builder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.Registries;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
        double y = 0.1;

        target.addVelocity(x, y, z);
        target.velocityModified = true;
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
