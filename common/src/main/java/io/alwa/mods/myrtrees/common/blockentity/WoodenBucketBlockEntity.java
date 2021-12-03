package io.alwa.mods.myrtrees.common.blockentity;

import io.alwa.mods.myrtrees.common.MyrtreesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WoodenBucketBlockEntity extends BlockEntity {
    public int latex;

    public WoodenBucketBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MyrtreesBlockEntities.WOODEN_BUCKET.get(), blockPos, blockState);
    }


    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        latex = compoundTag.getInt("latex");
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }


    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("latex", latex);
    }

    public boolean canInsert(int latexAmount) {
        return latex + latexAmount <= MyrtreesConfig.BUCKET_CAPACITY;
    }

}
