package io.github.mumboteam.spooncraftadditions.material;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class SpooncraftMaterial {
    public static final ToolMaterial MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            6093,
            9.0F,
            4.0F,
            15,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );
}
