package io.alwa.myrtrees.common.blockentity;

import io.alwa.myrtrees.common.Myrtrees;
import io.alwa.myrtrees.common.block.TreeTapBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreeTapBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int TRANSFER_RATE = 2;
    private int TRANSFER_TICKS = 5;

    public TreeTapBlockEntity() {
        super(Myrtrees.TREE_TAP_ENTITY_TYPE.get());
    }

    @Override
    public void tick() {
        BlockState tap = this.getBlockState();
        Direction facing = tap.getValue(TreeTapBlock.FACING);

        BlockEntity bucket = level.getBlockEntity(getBlockPos().below());
        BlockEntity log = level.getBlockEntity(getBlockPos().relative(facing, 1));

        if (log instanceof RubberWoodBlockEntity && bucket instanceof WoodenBucketBlockEntity && level.getGameTime() % TRANSFER_TICKS == 0) {
            if (((WoodenBucketBlockEntity) bucket).canInsert(TRANSFER_RATE) && ((RubberWoodBlockEntity) log).latex >= TRANSFER_RATE) {
                level.setBlock(getBlockPos(), tap.setValue(TreeTapBlock.FLOWING, true), 2);
                ((WoodenBucketBlockEntity) bucket).latex += TRANSFER_RATE;
                ((RubberWoodBlockEntity) log).latex -= TRANSFER_RATE;
            } else if(tap.getValue(TreeTapBlock.FLOWING)) {
                level.setBlock(getBlockPos(), tap.setValue(TreeTapBlock.FLOWING, false), 2);
            }
        }

    }
}
