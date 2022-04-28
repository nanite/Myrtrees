package io.alwa.mods.myrtrees.common.block;

import dev.architectury.registry.registries.DeferredRegister;
import io.alwa.mods.myrtrees.common.Myrtrees;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface MyrtreesBlocks {
    DeferredRegister<Block> REGISTRY = DeferredRegister.create(Myrtrees.MOD_ID, Registry.BLOCK_REGISTRY);

    Supplier<Block> RUBBERWOOD_LOG = REGISTRY.register("rubberwood_log", RubberwoodLogBlock::new);
    Supplier<Block> RUBBERWOOD_PLANKS = REGISTRY.register("rubberwood_planks", RubberwoodPlanksBlock::new);
    Supplier<Block> RUBBERWOOD_LEAVES = REGISTRY.register("rubberwood_leaves", RubberwoodLeavesBlock::new);
    Supplier<Block> RUBBERWOOD_SAPLING = REGISTRY.register("rubberwood_sapling", RubberwoodSaplingBlock::new);
    Supplier<Block> FILLED_RUBBERWOOD_LOG = REGISTRY.register("filled_rubberwood_log", FilledRubberwoodLogBlock::new);
    Supplier<Block> TREE_TAP = REGISTRY.register("tree_tap", TreeTapBlock::new);
    Supplier<Block> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", WoodenBucketBlock::new);

}
