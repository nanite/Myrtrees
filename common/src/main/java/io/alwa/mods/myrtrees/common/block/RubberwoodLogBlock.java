package io.alwa.mods.myrtrees.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class RubberwoodLogBlock extends RotatedPillarBlock {
    public RubberwoodLogBlock() {
        super(Properties.copy(Blocks.JUNGLE_LOG));
    }
}
