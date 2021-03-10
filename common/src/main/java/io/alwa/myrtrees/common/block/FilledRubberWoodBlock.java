package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.Myrtrees;
import io.alwa.myrtrees.common.blockentity.RubberWoodBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FilledRubberWoodBlock extends RubberWoodBlock implements EntityBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new RubberWoodBlockEntity();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(Myrtrees.RUBBER_WOOD.get().asItem());
    }
}
