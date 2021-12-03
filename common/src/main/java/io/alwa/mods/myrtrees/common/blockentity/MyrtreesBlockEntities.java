package io.alwa.mods.myrtrees.common.blockentity;

import dev.architectury.hooks.block.BlockEntityHooks;
import dev.architectury.registry.registries.DeferredRegister;
import io.alwa.mods.myrtrees.common.Myrtrees;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public interface MyrtreesBlockEntities {
    DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Myrtrees.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    Supplier<BlockEntityType<?>> FILLED_RUBBERWOOD_LOG = REGISTRY.register("filled_rubberwood_log", () -> BlockEntityHooks.builder(FilledRubberwoodLogBlockEntity::new, MyrtreesBlocks.FILLED_RUBBERWOOD_LOG.get()).build(null));
    Supplier<BlockEntityType<?>> TREE_TAP = REGISTRY.register("tree_tap", () -> BlockEntityHooks.builder(TreeTapBlockEntity::new, MyrtreesBlocks.TREE_TAP.get()).build(null));
    Supplier<BlockEntityType<WoodenBucketBlockEntity>> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", () -> BlockEntityHooks.builder(WoodenBucketBlockEntity::new, MyrtreesBlocks.WOODEN_BUCKET.get()).build(null));
}
