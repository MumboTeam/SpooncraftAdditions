package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpooncraftSword extends SwordItem implements PolymerItem {
    private final PolymerModelData[] cmds;

    public SpooncraftSword(Settings settings) {
        super(SpooncraftMaterial.INSTANCE, settings.fireproof().rarity(Rarity.EPIC).component(ModComponents.SWORD_VARIANT, 0).attributeModifiers(SwordItem.createAttributeModifiers(SpooncraftMaterial.INSTANCE, 3, -2.4F)));
        this.cmds = new PolymerModelData[]{
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/1")),
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/2")),
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/3")),
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/4")),
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/5")),
                PolymerResourcePackUtils.requestModel(Items.NETHERITE_SWORD, Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/6"))
        };
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, @Nullable ServerPlayerEntity player) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.spooncraft_sword.desc").formatted(Formatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.NETHERITE_SWORD;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.cmds[itemStack.get(ModComponents.SWORD_VARIANT)].value();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.set(ModComponents.SWORD_VARIANT, (stack.get(ModComponents.SWORD_VARIANT) + 1) % cmds.length);
        return super.postHit(stack, target, attacker);
    }
}
