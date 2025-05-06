package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class EggBasket extends BundleItem implements PolymerItem {

    public EggBasket(Settings settings) {
        super(settings.maxCount(1).maxDamage(BundleItem.DEFAULT_MAX_COUNT).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT));
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, PacketContext context) {
        tooltip.addFirst(Text.translatable("item.spooncraftadditions.egg_basket.desc").formatted(Formatting.GRAY));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.BUNDLE;
    }
}
