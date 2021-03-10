package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.blockentity.WoodenBucketBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WoodenBucketBlock extends Block implements EntityBlock {
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 11.0D, 15.0D);
    }

    public WoodenBucketBlock() {
        super(Properties.copy(Blocks.JUNGLE_WOOD));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new WoodenBucketBlockEntity();
    }
}
