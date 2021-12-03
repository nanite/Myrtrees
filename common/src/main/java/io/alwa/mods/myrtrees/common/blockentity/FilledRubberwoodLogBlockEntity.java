package io.alwa.mods.myrtrees.common.blockentity;

import io.alwa.mods.myrtrees.common.MyrtreesConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FilledRubberwoodLogBlockEntity extends BlockEntity {
    public int latex;

    public FilledRubberwoodLogBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MyrtreesBlockEntities.FILLED_RUBBERWOOD_LOG.get(), blockPos, blockState);
        this.latex = MyrtreesConfig.TREE_CAPACITY;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        latex = compoundTag.getInt("latex");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("latex", latex);
    }


}
