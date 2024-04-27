package com.dayofpi.bitty.data.providers;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.data.conditions.*;
import com.dayofpi.bitty.item.BittyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BittyRecipeProvider extends FabricRecipeProvider {
    public BittyRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Items.CARROT), RecipeCategory.FOOD, BittyItems.ROASTED_CARROT, 0.35f, 200).criterion(hasItem(Items.CARROT), VanillaRecipeProvider.conditionsFromItem(Items.CARROT)).offerTo(withConditions(exporter, new RoastedCarrotsResourceCondition(true)), new Identifier(Bitty.MOD_ID, "roasted_carrot"));
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(Items.CARROT), RecipeCategory.FOOD, BittyItems.ROASTED_CARROT, 0.35f, 100).criterion(hasItem(Items.CARROT), VanillaRecipeProvider.conditionsFromItem(Items.CARROT)).offerTo(withConditions(exporter, new RoastedCarrotsResourceCondition(true)), new Identifier(Bitty.MOD_ID, "roasted_carrot_from_smoking"));
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(Items.CARROT), RecipeCategory.FOOD, BittyItems.ROASTED_CARROT, 0.35f, 600).criterion(hasItem(Items.CARROT), VanillaRecipeProvider.conditionsFromItem(Items.CARROT)).offerTo(withConditions(exporter, new RoastedCarrotsResourceCondition(true)), new Identifier(Bitty.MOD_ID, "roasted_carrot_from_campfire_cooking"));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(BittyItems.CALAMARI), RecipeCategory.FOOD, BittyItems.COOKED_CALAMARI, 0.35f, 100).criterion(hasItem(BittyItems.CALAMARI), VanillaRecipeProvider.conditionsFromItem(BittyItems.CALAMARI)).offerTo(withConditions(exporter, new CalamariResourceCondition(true)), new Identifier(Bitty.MOD_ID, "cooked_calamari"));
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(BittyItems.CALAMARI), RecipeCategory.FOOD, BittyItems.COOKED_CALAMARI, 0.35f, 50).criterion(hasItem(BittyItems.CALAMARI), VanillaRecipeProvider.conditionsFromItem(BittyItems.CALAMARI)).offerTo(withConditions(exporter, new CalamariResourceCondition(true)), new Identifier(Bitty.MOD_ID, "cooked_calamari_from_smoking"));
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(BittyItems.CALAMARI), RecipeCategory.FOOD, BittyItems.COOKED_CALAMARI, 0.35f, 300).criterion(hasItem(BittyItems.CALAMARI), VanillaRecipeProvider.conditionsFromItem(BittyItems.CALAMARI)).offerTo(withConditions(exporter, new CalamariResourceCondition(true)), new Identifier(Bitty.MOD_ID, "cooked_calamari_from_campfire_cooking"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, BittyItems.APPLE_PIE).input(Items.APPLE).input(Items.SUGAR).input(Items.EGG).criterion(hasItem(Items.APPLE), VanillaRecipeProvider.conditionsFromItem(Items.APPLE)).offerTo(withConditions(exporter, new ApplePiesResourceCondition(true)), new Identifier(Bitty.MOD_ID, "apple_pie"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.RABBIT_STEW).input(Items.BAKED_POTATO).input(Items.COOKED_RABBIT).input(Items.BOWL).input(BittyItems.ROASTED_CARROT).input(Ingredient.ofItems(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM)).criterion(hasItem(Items.COOKED_RABBIT), VanillaRecipeProvider.conditionsFromItem(Items.COOKED_RABBIT)).offerTo(withConditions(exporter, new RoastedCarrotsResourceCondition(true)), new Identifier(Bitty.MOD_ID, "rabbit_stew"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEATHER, 4).input(BittyItems.HIDE).group("leather").criterion(hasItem(BittyItems.HIDE), conditionsFromItem(BittyItems.HIDE)).offerTo(withConditions(exporter, new LeatherReworkResourceCondition()), new Identifier(Bitty.MOD_ID, "leather_from_hide"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEATHER, 2).input(BittyItems.PELT).group("leather").criterion(hasItem(BittyItems.PELT), conditionsFromItem(BittyItems.PELT)).offerTo(withConditions(exporter, new LeatherReworkResourceCondition()), new Identifier(Bitty.MOD_ID, "leather_from_pelt"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, Items.SADDLE).input('#', Items.LEATHER).input('X', Items.STRING).input('/', Items.IRON_INGOT).pattern("###").pattern("#X#").pattern("/ /").criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT)).offerTo(withConditions(exporter, new CraftableSaddleResourceCondition()), new Identifier(Bitty.MOD_ID, "saddle"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BittyItems.THATCH).input('#', BittyItems.REEDS).pattern("##").pattern("##").criterion(hasItem(BittyItems.REEDS), conditionsFromItem(BittyItems.REEDS)).offerTo(withConditions(exporter, new ReedsResourceCondition()), new Identifier(Bitty.MOD_ID, "thatch"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, BittyItems.REED_SEEDS, 3).input(Ingredient.ofItems(BittyItems.REEDS, BittyItems.LUMINOUS_REEDS)).criterion(hasItem(BittyItems.REEDS), conditionsFromItem(BittyItems.REEDS)).criterion(hasItem(BittyItems.LUMINOUS_REEDS), conditionsFromItem(BittyItems.LUMINOUS_REEDS)).offerTo(withConditions(exporter, new ReedsResourceCondition()), new Identifier(Bitty.MOD_ID, "reed_seeds"));
    }
}
