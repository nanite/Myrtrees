package io.alwa.mods.myrtrees.common.blockentity;

import io.alwa.mods.myrtrees.common.MyrtreesConfig;
import me.shedaniel.architectury.extensions.BlockEntityExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FilledRubberwoodLogBlockEntity extends BlockEntity implements BlockEntityExtension {
    public int latex;

    public FilledRubberwoodLogBlockEntity() {
        super(MyrtreesBlockEntities.FILLED_RUBBERWOOD_LOG.get());
        this.latex = MyrtreesConfig.TREE_CAPACITY;
    }

    @Override
    public void load(BlockState blockState, CompoundTag compoundTag) {
        super.load(blockState, compoundTag);
        latex = compoundTag.getInt("latex");
    }

    @Override
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
