package com.dayofpi.bitty.data;

import com.dayofpi.bitty.data.providers.*;
import com.dayofpi.bitty.world.BittyConfiguredFeatures;
import com.dayofpi.bitty.world.BittyPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BittyDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(BittyModelProvider::new);
		pack.addProvider(BittyRecipeProvider::new);
		pack.addProvider(BittyBiomeTagProvider::new);
		FabricTagProvider.BlockTagProvider blockTagProvider = pack.addProvider(BittyBlockTagProvider::new);
		pack.addProvider((output, registriesFuture) -> new BittyItemTagProvider(output, registriesFuture, blockTagProvider));
		pack.addProvider(BittyBlockLootProvider::new);
		pack.addProvider(BittyWorldGenProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, BittyConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, BittyPlacedFeatures::bootstrap);
	}
}
