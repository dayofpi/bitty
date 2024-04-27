package com.dayofpi.bitty.data.providers;

import com.dayofpi.bitty.item.BittyItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class BittyItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BittyItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, FabricTagProvider.BlockTagProvider blockTagProvider) {
        super(output, registriesFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(BittyItems.REED_SEEDS);
        getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(BittyItems.REED_SEEDS);
    }
}
