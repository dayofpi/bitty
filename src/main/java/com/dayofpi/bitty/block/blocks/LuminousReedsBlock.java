package com.dayofpi.bitty.block.blocks;

import com.dayofpi.bitty.particle.BittyParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LuminousReedsBlock extends ReedsBlock {
    public static final MapCodec<LuminousReedsBlock> CODEC = createCodec(LuminousReedsBlock::new);

    public LuminousReedsBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends TallPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(HALF) == DoubleBlockHalf.LOWER || random.nextInt(3) != 0)
            return;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int l = 0; l < 10; ++l) {
            mutable.set(x + MathHelper.nextInt(random, -10, 10), y + MathHelper.nextInt(random, -10, 10), z + MathHelper.nextInt(random, -10, 10));
            BlockState blockState = world.getBlockState(mutable);
            if (!blockState.isFullCube(world, mutable)) {
                world.addParticle(BittyParticles.FIREFLY, (double) mutable.getX() + random.nextDouble(), (double) mutable.getY() + random.nextDouble(), (double) mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
}
