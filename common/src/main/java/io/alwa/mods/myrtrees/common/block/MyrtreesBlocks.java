package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.Myrtrees;
import me.shedaniel.architectury.registry.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface MyrtreesBlocks {
    DeferredRegister<Block> REGISTRY = DeferredRegister.create(Myrtrees.MOD_ID, Registry.BLOCK_REGISTRY);

    Supplier<Block> RUBBER_WOOD = REGISTRY.register("rubber_wood", RubberWoodBlock::new);
    Supplier<Block> RUBBER_PLANKS = REGISTRY.register("rubber_planks", RubberPlanksBlock::new);
    Supplier<Block> RUBBER_LEAVES = REGISTRY.register("rubber_leaves", RubberLeavesBlock::new);
    Supplier<Block> RUBBER_SAPLING = REGISTRY.register("rubber_sapling", RubberSaplingBlock::new);
    Supplier<Block> FILLED_RUBBER_WOOD = REGISTRY.register("filled_rubber_wood", FilledRubberWoodBlock::new);
    Supplier<Block> TREE_TAP = REGISTRY.register("tree_tap", TreeTapBlock::new);
    Supplier<Block> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", WoodenBucketBlock::new);
}
