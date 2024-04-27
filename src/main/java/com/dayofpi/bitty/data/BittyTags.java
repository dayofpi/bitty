package com.dayofpi.bitty.data;

import com.dayofpi.bitty.Bitty;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class BittyTags {
    public static class Blocks {
        public static final TagKey<Block> REEDS_PLANTABLE_ON = tag("reeds_plantable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Bitty.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> EXTRA_RABBIT_SPAWNS = tag("extra_rabbit_spawns");

        private static TagKey<Biome> tag(String name) {
            return TagKey.of(RegistryKeys.BIOME, new Identifier(Bitty.MOD_ID, name));
        }
    }
}
