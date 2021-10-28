package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.worldgen.RubberwoodTreeGrower;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public class RubberwoodSaplingBlock extends SaplingBlock {
    public RubberwoodSaplingBlock() {
        super(new RubberwoodTreeGrower(), Properties.copy(Blocks.JUNGLE_SAPLING).noOcclusion());
    }
}
