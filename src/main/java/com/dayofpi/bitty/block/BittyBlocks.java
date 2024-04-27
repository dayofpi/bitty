package com.dayofpi.bitty.block;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.block.blocks.LuminousReedsBlock;
import com.dayofpi.bitty.block.blocks.ReedCropBlock;
import com.dayofpi.bitty.block.blocks.ReedsBlock;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.HayBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BittyBlocks {
    public static final Block THATCH = block("thatch", new HayBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BANJO).strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block REEDS = block("reeds", new ReedsBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LUMINOUS_REEDS = block("luminous_reeds", new LuminousReedsBlock(AbstractBlock.Settings.copy(REEDS).luminance(value -> 5)));
    public static final Block REED_CROP = block("reed_crop", new ReedCropBlock(AbstractBlock.Settings.copy(REEDS).ticksRandomly()));

    private static Block block(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Bitty.MOD_ID, name), block);
    }

    public static void init() {
        FlammableBlockRegistry.getDefaultInstance().add(THATCH, 60, 20);
    }
}
