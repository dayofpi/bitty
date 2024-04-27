package com.dayofpi.bitty.data.providers;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.item.BittyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.state.property.Properties;

public class BittyModelProvider extends FabricModelProvider {
    public BittyModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerAxisRotated(BittyBlocks.THATCH, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);
        blockStateModelGenerator.registerDoubleBlock(BittyBlocks.REEDS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(BittyBlocks.LUMINOUS_REEDS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerCrop(BittyBlocks.REED_CROP, Properties.AGE_2, 0, 1, 2);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(BittyItems.APPLE_PIE, Models.GENERATED);
        itemModelGenerator.register(BittyItems.CALAMARI, Models.GENERATED);
        itemModelGenerator.register(BittyItems.COOKED_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(BittyItems.ROASTED_CARROT, Models.GENERATED);
        itemModelGenerator.register(BittyItems.HIDE, Models.GENERATED);
        itemModelGenerator.register(BittyItems.PELT, Models.GENERATED);
    }
}
