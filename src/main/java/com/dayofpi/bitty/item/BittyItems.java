package com.dayofpi.bitty.item;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.block.BittyBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BittyItems {
    public static final Item THATCH = blockItem(BittyBlocks.THATCH);
    public static final Item REEDS = blockItem(BittyBlocks.REEDS);
    public static final Item LUMINOUS_REEDS = blockItem(BittyBlocks.LUMINOUS_REEDS);
    public static final Item REED_SEEDS = item("reed_seeds", new AliasedBlockItem(BittyBlocks.REED_CROP, new Item.Settings()));
    public static final Item APPLE_PIE = item("apple_pie", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build())));
    public static final Item CALAMARI = item("calamari", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build())));
    public static final Item COOKED_CALAMARI = item("cooked_calamari", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.5f).build())));
    public static final Item ROASTED_CARROT = item("roasted_carrot", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f).build())));
    public static final Item HIDE = item("hide");
    public static final Item PELT = item("pelt");

    private static Item blockItem(Block block) {
        return item(Registries.BLOCK.getId(block).getPath(), new BlockItem(block, new Item.Settings()));
    }

    private static Item item(String name) {
        return item(name, new Item(new Item.Settings()));
    }

    private static Item item(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Bitty.MOD_ID, name), item);
    }

    public static void init() {
        CompostingChanceRegistry.INSTANCE.add(THATCH, 0.85F);
        CompostingChanceRegistry.INSTANCE.add(REEDS, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(LUMINOUS_REEDS, 0.5F);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            if (BittyConfig.reeds) {
                entries.addAfter(Items.LARGE_FERN, REEDS, LUMINOUS_REEDS);
                entries.addAfter(Items.BEETROOT_SEEDS, REED_SEEDS);
                entries.addAfter(Items.HAY_BLOCK, THATCH);
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            if (BittyConfig.applePies) {
                entries.addBefore(Items.PUMPKIN_PIE, APPLE_PIE);
            }
            if (BittyConfig.calamari) {
                entries.addBefore(Items.COD, CALAMARI, COOKED_CALAMARI);
            }
            if (BittyConfig.roastedCarrots) {
                entries.addAfter(Items.CARROT, ROASTED_CARROT);
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            if (BittyConfig.leatherRework) {
                entries.getDisplayStacks().removeIf(itemStack -> itemStack.isOf(Items.RABBIT_HIDE));
                entries.getSearchTabStacks().removeIf(itemStack -> itemStack.isOf(Items.RABBIT_HIDE));
                entries.addBefore(Items.LEATHER, HIDE, PELT);
            }
        });
    }
}
