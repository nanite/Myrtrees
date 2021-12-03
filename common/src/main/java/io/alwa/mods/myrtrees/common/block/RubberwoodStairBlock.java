package io.alwa.mods.myrtrees.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;

public class RubberwoodStairBlock extends StairBlock {
    // Jungle planks used as rubberwood planks inaccessible during construction
    public RubberwoodStairBlock() {
        super(Blocks.JUNGLE_PLANKS.defaultBlockState(), Properties.copy(Blocks.JUNGLE_PLANKS));
    }
}
