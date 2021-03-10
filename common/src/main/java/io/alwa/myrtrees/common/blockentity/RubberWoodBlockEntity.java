package io.alwa.myrtrees.common.blockentity;

import io.alwa.myrtrees.common.Myrtrees;
import me.shedaniel.architectury.extensions.BlockEntityExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class RubberWoodBlockEntity extends BlockEntity implements BlockEntityExtension {
    public int latex;

    public RubberWoodBlockEntity() {
        super(Myrtrees.RUBBER_WOOD_ENTITY_TYPE.get());
        this.latex = 10000;
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
