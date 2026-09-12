package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.Equippable;

import java.util.List;

public class DragonShield extends ShieldItem implements PolymerItem {
    public DragonShield(Properties properties) {
        super(properties
                .rarity(Rarity.UNCOMMON)
                .stacksTo(1)
                .durability(336)
                .component(
                        DataComponents.EQUIPPABLE,
                        Equippable.builder(EquipmentSlot.OFFHAND).build()
                )
                .component(
                        DataComponents.CONSUMABLE,
                        net.minecraft.world.item.component.Consumable.builder()
                                .consumeSeconds(72000)
                                .animation(ItemUseAnimation.BLOCK)
                                .build()
                )
                .delayedComponent(
                        DataComponents.BLOCKS_ATTACKS,
                        (context) -> new net.minecraft.world.item.component.BlocksAttacks(
                                0.25F, // blockDelaySeconds
                                1.0F,  // disableCooldownScale
                                java.util.List.of(
                                        new net.minecraft.world.item.component.BlocksAttacks.DamageReduction(
                                                90.0F, // Block angle
                                                java.util.Optional.empty(), // No specific damage type required to reduce
                                                0.0F,
                                                1.0F // 100% damage reduction
                                        )
                                ),
                                new net.minecraft.world.item.component.BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                java.util.Optional.of(context.getOrThrow(net.minecraft.tags.DamageTypeTags.BYPASSES_SHIELD)),
                                java.util.Optional.of(SoundEvents.SHIELD_BLOCK),
                                java.util.Optional.of(SoundEvents.SHIELD_BREAK)
                        )
                ));
    }

    @Override
    public void modifyClientTooltip(List<Component> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Component.translatable("item.spooncraftadditions.dragon_shield.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.SHIELD;
    }
}
