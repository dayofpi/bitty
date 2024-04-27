package com.dayofpi.bitty.data;

import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.BittyUtil;
import com.dayofpi.bitty.item.BittyItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class BittyLootTableEvents {
    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (!source.isBuiltin())
                return;
            if (BittyConfig.calamari) {
                if (key.equals(EntityType.SQUID.getLootTableId()) || key.equals(EntityType.GLOW_SQUID.getLootTableId())) {
                    LootPool.Builder lootPoolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(BittyItems.CALAMARI)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, BittyUtil.NEEDS_ENTITY_ON_FIRE))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)));
                    tableBuilder.pool(lootPoolBuilder);
                }
            }
            if (BittyConfig.nautilusShellsFromArchaeology) {
                if (LootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(key) || LootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(key)) {
                    tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(Items.NAUTILUS_SHELL)));
                }
            }
        });
        LootTableEvents.REPLACE.register((key, original, source) -> {
            if (!source.isBuiltin())
                return null;
            if (BittyConfig.shearOnlyMossCarpets && key.equals(Blocks.MOSS_CARPET.getLootTableKey())) {
                return BlockLootTableGenerator.dropsWithShears(Items.MOSS_CARPET).build();
            }
            if (BittyConfig.leatherRework) {
                if (key.equals(LootTables.CAT_MORNING_GIFT_GAMEPLAY)) {
                    return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(BittyItems.PELT).weight(10)).with(ItemEntry.builder(Items.RABBIT_FOOT).weight(10)).with(ItemEntry.builder(Items.CHICKEN).weight(10)).with(ItemEntry.builder(Items.FEATHER).weight(10)).with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(10)).with(ItemEntry.builder(Items.STRING).weight(10)).with(ItemEntry.builder(Items.PHANTOM_MEMBRANE).weight(2))).build();
                }
                if (key.equals(EntityType.COW.getLootTableId()) || key.equals(EntityType.MOOSHROOM.getLootTableId())) {
                    return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(BittyItems.HIDE).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.BEEF).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, BittyUtil.NEEDS_ENTITY_ON_FIRE))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))).build();
                }
                if (key.equals(EntityType.HORSE.getLootTableId()) || key.equals(EntityType.DONKEY.getLootTableId()) || key.equals(EntityType.MULE.getLootTableId()) || key.equals(EntityType.LLAMA.getLootTableId()) || key.equals(EntityType.CAMEL.getLootTableId())) {
                    return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(BittyItems.HIDE).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f))))).build();
                }
                if (key.equals(EntityType.HOGLIN.getLootTableId())) {
                    return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(Items.PORKCHOP).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, BittyUtil.NEEDS_ENTITY_ON_FIRE))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f))))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(BittyItems.HIDE).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f))))).build();
                }
                if (key.equals(EntityType.RABBIT.getLootTableId())) {
                    return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(BittyItems.PELT).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.RABBIT).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, BittyUtil.NEEDS_ENTITY_ON_FIRE))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(Items.RABBIT_FOOT)).conditionally(KilledByPlayerLootCondition.builder()).conditionally(RandomChanceWithLootingLootCondition.builder(0.1F, 0.03F))).build();
                }
            }
            return null;
        });
    }
}
