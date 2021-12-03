package io.alwa.mods.myrtrees.common.blockentity;

import io.alwa.mods.myrtrees.common.MyrtreesConfig;
import io.alwa.mods.myrtrees.common.block.TreeTapBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreeTapBlockEntity extends BlockEntity implements TickableBlockEntity {
    public TreeTapBlockEntity() {
        super(MyrtreesBlockEntities.TREE_TAP.get());
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
        if (!(blockEntity instanceof TreeTapBlockEntity)) return;
        BlockState tap = blockEntity.getBlockState();
        Direction facing = tap.getValue(TreeTapBlock.FACING);

        BlockEntity bucket = level.getBlockEntity(blockEntity.getBlockPos().below());
        BlockEntity log = level.getBlockEntity(blockEntity.getBlockPos().relative(facing, 1));

        if (log instanceof FilledRubberwoodLogBlockEntity && bucket instanceof WoodenBucketBlockEntity && level.getGameTime() % MyrtreesConfig.TAP_TRANSFER_TICKS == 0) {
            if (((WoodenBucketBlockEntity) bucket).canInsert(MyrtreesConfig.TAP_TRANSFER_RATE) && ((FilledRubberwoodLogBlockEntity) log).latex >= MyrtreesConfig.TAP_TRANSFER_RATE) {
                level.setBlock(blockEntity.getBlockPos(), tap.setValue(TreeTapBlock.FLOWING, true), 2);
                ((WoodenBucketBlockEntity) bucket).latex += MyrtreesConfig.TAP_TRANSFER_RATE;
                ((FilledRubberwoodLogBlockEntity) log).latex -= MyrtreesConfig.TAP_TRANSFER_RATE;
                bucket.setChanged();
                level.sendBlockUpdated(bucket.getBlockPos(), bucket.getBlockState(), bucket.getBlockState(), 11);
            } else if (tap.getValue(TreeTapBlock.FLOWING)) {
                level.setBlock(blockEntity.getBlockPos(), tap.setValue(TreeTapBlock.FLOWING, false), 2);
            }
        }

    }
}
