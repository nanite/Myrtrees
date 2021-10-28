package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.blockentity.FilledRubberwoodLogBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FilledRubberwoodLogBlock extends RubberwoodLogBlock implements EntityBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new FilledRubberwoodLogBlockEntity();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(MyrtreesBlocks.RUBBERWOOD_LOG.get().asItem());
    }
}
