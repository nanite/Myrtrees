package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.blockentity.RubberWoodBlockEntity;
import io.alwa.mods.myrtrees.common.blockentity.TreeTapBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TreeTapBlock extends DirectionalBlock implements EntityBlock {
    public static final VoxelShape SHAPE_EAST = Block.box(13.0D, -1.0D, 6.0D, 16.0D, 4.0D, 10.0D);
    public static final VoxelShape SHAPE_WEST = Block.box(0.0D, -1.0D, 6.0D, 3.0D, 4.0D, 10.0D);
    public static final VoxelShape SHAPE_SOUTH = Block.box(6.0D, -1.0D, 13.0D, 10.0D, 4.0D, 16.0D);
    public static final VoxelShape SHAPE_NORTH = Block.box(6.0D, -1.0D, 0.0D, 10.0D, 4.0D, 3.0D);

    public static final BooleanProperty FLOWING = BooleanProperty.create("flowing");

    public TreeTapBlock() {
        super(Properties.copy(Blocks.HOPPER));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new TreeTapBlockEntity();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        if (blockPlaceContext.getClickedFace().getAxis() == Direction.Axis.Y) {
            return null;
        }
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getClickedFace().getOpposite()).setValue(FLOWING, false);
    }

    private boolean canAttachTo(BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockGetter.getBlockEntity(blockPos) instanceof RubberWoodBlockEntity;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return !this.canSurvive(blockState, levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING);
        return this.canAttachTo(levelReader, blockPos.relative(direction), direction);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(FACING)) {
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            case SOUTH:
                return SHAPE_SOUTH;
            default:
                return SHAPE_NORTH;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FLOWING);
    }
}
