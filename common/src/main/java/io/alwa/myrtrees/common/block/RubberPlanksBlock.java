package io.alwa.myrtrees.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;

public class RubberPlanksBlock extends DirectionalBlock {
    public RubberPlanksBlock() {
        super(Properties.copy(Blocks.JUNGLE_PLANKS));
    }
}
