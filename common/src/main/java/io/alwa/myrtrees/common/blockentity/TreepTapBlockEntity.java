package io.alwa.myrtrees.common.blockentity;

import io.alwa.myrtrees.common.Myrtrees;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TreepTapBlockEntity extends BlockEntity {
    private int latex;

    public TreepTapBlockEntity() {
        super(Myrtrees.TREE_TAP_ENTITY_TYPE.get());
        this.latex = 1000;
    }

    public void load(BlockState blockState, CompoundTag compoundTag) {
        super.load(blockState, compoundTag);
        latex = compoundTag.getInt("latex");
    }

    public CompoundTag save(CompoundTag compoundTag) {
        super.save(compoundTag);
        compoundTag.putInt("latex", latex);
        return compoundTag;
    }

}
