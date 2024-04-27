package com.dayofpi.bitty.data.providers;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.item.BittyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BittyBlockLootProvider extends FabricBlockLootTableProvider {
    public BittyBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BittyBlocks.THATCH);
        addDrop(BittyBlocks.REEDS, block -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(BittyBlocks.LUMINOUS_REEDS, block -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(BittyBlocks.REED_CROP, BittyItems.REED_SEEDS);
    }
}
