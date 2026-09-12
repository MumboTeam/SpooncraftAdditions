package io.github.mumboteam.spooncraftadditions.datagen;

import io.github.mumboteam.spooncraftadditions.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DamageResistant;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.Repairable;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.equipment.ArmorMaterials.*;

public class SpooncraftAdditionsRecipeProvider extends FabricRecipeProvider {
    private static final ArmorMaterial[] ARMOR_MATERIALS = new ArmorMaterial[]{
            LEATHER,
            COPPER,
            IRON,
            GOLD,
            DIAMOND,
            NETHERITE
    };

    private static final Item[] HATS = new Item[]{
        ModItems.SKYBLOCK_HAT,
        ModItems.BALTOP_HAT,
        ModItems.ARROW_HAT,
        ModItems.SKYBLOCK_STARTER_BASE_HAT,
        ModItems.DEEP_VOID_SANCTUARY_HAT,
        ModItems.DUCK_HAT,
        ModItems.S5_SPAWN_HAT,
        ModItems.WINDMILL_HOUSE_HAT,
        ModItems.TAG_FIRST,
        ModItems.TAG_SECOND,
        ModItems.TAG_THIRD,
        ModItems.TAG_PARTICIPATION,
        ModItems.REDSTONE_HAT,
        ModItems.PIRATE_HAT,
        ModItems.WREATH_HAT,
        ModItems.JESTER_HAT,
        ModItems.PUMPKIN_HAT
    };

    public static @Nullable Item getVanillaArmorItem(ArmorMaterial material, ArmorType type) {
        String name = material.assetId().identifier().getPath();

        return switch (name) {
            case "leather" -> switch (type) {
                case HELMET -> Items.LEATHER_HELMET;
                case CHESTPLATE -> Items.LEATHER_CHESTPLATE;
                case LEGGINGS -> Items.LEATHER_LEGGINGS;
                case BOOTS -> Items.LEATHER_BOOTS;
                default -> null;
            };
            case "copper" -> switch (type) {
                case HELMET -> Items.COPPER_HELMET;
                case CHESTPLATE -> Items.COPPER_CHESTPLATE;
                case LEGGINGS -> Items.COPPER_LEGGINGS;
                case BOOTS -> Items.COPPER_BOOTS;
                default -> null;
            };
            case "iron" -> switch (type) {
                case HELMET -> Items.IRON_HELMET;
                case CHESTPLATE -> Items.IRON_CHESTPLATE;
                case LEGGINGS -> Items.IRON_LEGGINGS;
                case BOOTS -> Items.IRON_BOOTS;
                default -> null;
            };
            case "gold" -> switch (type) {
                case HELMET -> Items.GOLDEN_HELMET;
                case CHESTPLATE -> Items.GOLDEN_CHESTPLATE;
                case LEGGINGS -> Items.GOLDEN_LEGGINGS;
                case BOOTS -> Items.GOLDEN_BOOTS;
                default -> null;
            };
            case "diamond" -> switch (type) {
                case HELMET -> Items.DIAMOND_HELMET;
                case CHESTPLATE -> Items.DIAMOND_CHESTPLATE;
                case LEGGINGS -> Items.DIAMOND_LEGGINGS;
                case BOOTS -> Items.DIAMOND_BOOTS;
                default -> null;
            };
            case "netherite" -> switch (type) {
                case HELMET -> Items.NETHERITE_HELMET;
                case CHESTPLATE -> Items.NETHERITE_CHESTPLATE;
                case LEGGINGS -> Items.NETHERITE_LEGGINGS;
                case BOOTS -> Items.NETHERITE_BOOTS;
                default -> null;
            };
            default -> null;
        };
    }

    public SpooncraftAdditionsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                for (Item hat : HATS) {
                    for (ArmorMaterial material : ARMOR_MATERIALS) {
                        cosmeticSmithing(hat, RecipeCategory.COMBAT, material, ArmorType.HELMET);
                    }
                }
            }

            public void cosmeticSmithing(Item base, RecipeCategory category, ArmorMaterial material, ArmorType armorType) {
                ItemStackTemplate result = new ItemStackTemplate(base, asComponents(material, armorType));
                Item armorItem = getVanillaArmorItem(material, armorType);

                new SmithingTransformRecipeBuilder(
                                Ingredient.of(ModItems.COSMETIC_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(base), Ingredient.of(armorItem), category, result
                        )
                        .unlocks(getHasName(base), has(base))
                        .unlocks(getHasName(armorItem), has(armorItem))
                        .save(this.output, getItemName(base) + "_" + material.assetId().identifier().getPath() + "_smithing");
            }

            public DataComponentPatch asComponents(ArmorMaterial material, ArmorType armorType) {
                DataComponentPatch.Builder builder = DataComponentPatch.builder();

                if (armorType != null) {
                    builder.set(DataComponents.DAMAGE, 0);
                    builder.set(DataComponents.MAX_DAMAGE, armorType.getDurability(material.durability()));
                    builder.set(DataComponents.ATTRIBUTE_MODIFIERS, material.createAttributes(armorType));
                }
                builder.set(DataComponents.REPAIRABLE, new Repairable(registries.lookupOrThrow(Registries.ITEM).getOrThrow(material.repairIngredient())));
                builder.set(DataComponents.ENCHANTABLE, new Enchantable(material.enchantmentValue()));

                if (material == ArmorMaterials.NETHERITE) {
                    builder.set(DataComponents.DAMAGE_RESISTANT, new DamageResistant(registries.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypeTags.IS_FIRE)));
                }

                return builder.build();
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "SpooncraftAdditionsRecipeProvider";
    }
}
