package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.blockentity.RubberWoodBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class FilledRubberWoodBlock extends RubberWoodBlock implements EntityBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new RubberWoodBlockEntity();
    }
}
