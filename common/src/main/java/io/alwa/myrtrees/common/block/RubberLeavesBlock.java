package io.alwa.myrtrees.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;

public class RubberLeavesBlock extends LeavesBlock {
    public RubberLeavesBlock() {
        super(Properties.copy(Blocks.JUNGLE_LEAVES));
    }
}
