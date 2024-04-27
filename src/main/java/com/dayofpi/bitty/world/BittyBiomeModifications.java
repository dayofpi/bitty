package com.dayofpi.bitty.world;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.data.BittyTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class BittyBiomeModifications {
    public static void init() {
        BiomeModifications.addFeature(biomeSelectionContext -> BittyConfig.reeds && biomeSelectionContext.hasTag(ConventionalBiomeTags.IS_SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, BittyPlacedFeatures.PATCH_REEDS);

        BiomeModifications.create(new Identifier(Bitty.MOD_ID, "birch_overhaul"))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> BittyConfig.birchOverhaul && biomeSelectionContext.hasTag(ConventionalBiomeTags.IS_BIRCH_FOREST), context -> {
                    context.getEffects().setGrassColor(10475854);
                });

        BiomeModifications.create(new Identifier(Bitty.MOD_ID, "extra_rabbit_spawns"))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> BittyConfig.extraRabbitSpawns > 0 && biomeSelectionContext.hasTag(BittyTags.Biomes.EXTRA_RABBIT_SPAWNS), context -> {
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.RABBIT);
                    context.getSpawnSettings().addSpawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, BittyConfig.extraRabbitSpawns, 2, 3));
                });

        BiomeModifications.create(new Identifier(Bitty.MOD_ID, "ocelot_as_cat_variant"))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> BittyConfig.ocelotAsCatVariant && biomeSelectionContext.getBiomeKey().equals(BiomeKeys.JUNGLE), context -> {
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.OCELOT);
                    context.getSpawnSettings().addSpawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAT, 10, 1, 1));
                });
    }
}
