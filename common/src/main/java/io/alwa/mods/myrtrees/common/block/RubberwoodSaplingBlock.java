package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.RubberTreeGeneration;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public class RubberwoodSaplingBlock extends SaplingBlock {
    public RubberwoodSaplingBlock() {
        super(new RubberTreeGeneration.RubberWoodTree(), Properties.copy(Blocks.JUNGLE_SAPLING).noOcclusion());
    }
    
}
