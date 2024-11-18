package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import io.github.mumboteam.spooncraftadditions.SpooncraftAdditions;
import io.github.mumboteam.spooncraftadditions.component.ModComponents;
import io.github.mumboteam.spooncraftadditions.material.SpooncraftMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class SpooncraftSword extends SwordItem implements PolymerItem {
    private final Identifier[] cmds;

    public SpooncraftSword(Settings settings) {
        super(SpooncraftMaterial.MATERIAL, 3, -2.4F, settings.fireproof().rarity(Rarity.EPIC).component(ModComponents.SWORD_VARIANT, 0));
        this.cmds = new Identifier[]{
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/1")),
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/2")),
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/3")),
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/4")),
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/5")),
                PolymerResourcePackUtils.getBridgedModelId(Identifier.of(SpooncraftAdditions.ID, "item/spooncraft_sword/6"))
        };
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.spooncraft_sword.desc").formatted(Formatting.RED));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.NETHERITE_SWORD;
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return this.cmds[stack.get(ModComponents.SWORD_VARIANT)];
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.set(ModComponents.SWORD_VARIANT, (stack.get(ModComponents.SWORD_VARIANT) + 1) % cmds.length);
        return super.postHit(stack, target, attacker);
    }
}
