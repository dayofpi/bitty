package com.dayofpi.bitty.block.blocks;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.data.BittyTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ReedCropBlock extends PlantBlock implements FluidFillable, Fertilizable {
    public static final MapCodec<ReedCropBlock> CODEC = createCodec(ReedCropBlock::new);
    public static final IntProperty AGE = Properties.AGE_2;

    public ReedCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            advanceReedStage(state, world, pos, random);
        }
    }

    private static void advanceReedStage(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState finalState = random.nextInt(5) == 0 ? BittyBlocks.LUMINOUS_REEDS.getDefaultState() : BittyBlocks.REEDS.getDefaultState();
        boolean max = state.get(AGE) == 2;
        BlockState nextState = max ? finalState : state.with(AGE, Math.min(state.get(AGE) + random.nextInt(2) + 1, 2));
        BlockPos above = pos.up();
        if (max && world.isAir(above)) {
            world.setBlockState(pos, nextState.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER).with(Properties.WATERLOGGED, true), 2);
            world.setBlockState(above, nextState.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER).with(Properties.WATERLOGGED, false), 2);
        } else {
            world.setBlockState(pos, nextState);
        }
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BittyTags.Blocks.REEDS_PLANTABLE_ON);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        if (fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8) {
            return super.getPlacementState(ctx);
        }
        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState blockState = super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        if (!blockState.isAir()) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return blockState;
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return state.get(AGE) != 2 || world.isAir(pos.up());
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        advanceReedStage(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getStill(false);
    }
}
