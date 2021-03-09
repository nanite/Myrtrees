package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.worldgen.RubberTreeGrower;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public class RubberSaplingBlock extends SaplingBlock {

    public RubberSaplingBlock() {
        super(new RubberTreeGrower(), Properties.copy(Blocks.JUNGLE_SAPLING).noOcclusion());
    }
}
