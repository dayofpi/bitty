package com.dayofpi.bitty.world;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.block.BittyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class BittyConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_DARK_OAK = key("small_dark_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_REEDS = key("patch_reeds");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {
        register(registerable, SMALL_DARK_OAK, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.DARK_OAK_LOG), new StraightTrunkPlacer(4, 2, 1), BlockStateProvider.of(Blocks.DARK_OAK_LEAVES), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3), new TwoLayersFeatureSize(1, 0, 1)).build());
        register(registerable, PATCH_REEDS, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(96, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(BittyBlocks.REEDS.getDefaultState(), 6).add(BittyBlocks.LUMINOUS_REEDS.getDefaultState(), 1).build())), BlockPredicate.matchingFluids(Fluids.WATER))));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> key(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Bitty.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
