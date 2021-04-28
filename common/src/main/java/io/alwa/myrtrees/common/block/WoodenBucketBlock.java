package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.Myrtrees;
import io.alwa.myrtrees.common.blockentity.WoodenBucketBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof WoodenBucketBlockEntity) {
            WoodenBucketBlockEntity bucket = (WoodenBucketBlockEntity)blockEntity;
            if (!level.isClientSide && player.isCreative()) {
                ItemStack itemStack = new ItemStack(Myrtrees.WOODEN_BUCKET_ITEM.get());
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putInt("latex", bucket.latex);
                if (!compoundTag.isEmpty()) {
                    itemStack.addTagElement("BlockEntityTag", compoundTag);
                }

                ItemEntity itemEntity = new ItemEntity(level, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, itemStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new WoodenBucketBlockEntity();
    }
}
