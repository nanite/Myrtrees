package io.alwa.myrtrees.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class RubberWoodBlock extends RotatedPillarBlock {
    public RubberWoodBlock() {
        super(Properties.copy(Blocks.JUNGLE_LOG));
    }
}
