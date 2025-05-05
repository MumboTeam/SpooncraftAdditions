package io.github.mumboteam.spooncraftadditions.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.*;
import xyz.nucleoid.packettweaker.PacketContext;

public class EasterBasket extends BundleItem implements PolymerItem {

    public EasterBasket(Settings settings) {
        super(settings.maxCount(1).maxDamage(BundleItem.DEFAULT_MAX_COUNT).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.BUNDLE;
    }
}
