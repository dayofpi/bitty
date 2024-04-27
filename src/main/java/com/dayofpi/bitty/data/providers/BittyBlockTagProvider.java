package com.dayofpi.bitty.data.providers;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.data.BittyTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BittyBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BittyBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(BittyBlocks.THATCH);
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(BittyBlocks.REEDS, BittyBlocks.LUMINOUS_REEDS);
        getOrCreateTagBuilder(BittyTags.Blocks.REEDS_PLANTABLE_ON).addOptionalTag(BlockTags.DIRT).addOptionalTag(BlockTags.SAND).add(Blocks.CLAY);
    }
}
