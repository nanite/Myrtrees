package io.alwa.myrtrees.common.blockentity;

import io.alwa.myrtrees.common.Myrtrees;
import me.shedaniel.architectury.extensions.BlockEntityExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodenBucketBlockEntity extends BlockEntity implements BlockEntityExtension {
    public int MAX_LATEX = 10000;
    public int latex;

    public WoodenBucketBlockEntity() {
        super(Myrtrees.WOODEN_BUCKET_ENTITY_TYPE.get());
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

    public boolean canInsert(int latexAmount) {
        return latex + latexAmount <= MAX_LATEX;
    }

    @Override
    public void loadClientData(@NotNull BlockState pos, @NotNull CompoundTag tag) {
        latex = tag.getInt("latex");
    }

    @Override
    public @NotNull CompoundTag saveClientData(@NotNull CompoundTag tag) {
        tag.putInt("latex", latex);
        return tag;
    }
}
