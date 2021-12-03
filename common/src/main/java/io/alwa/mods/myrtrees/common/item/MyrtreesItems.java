package io.alwa.mods.myrtrees.common.item;

import io.alwa.mods.myrtrees.common.Myrtrees;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import me.shedaniel.architectury.registry.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface MyrtreesItems {
    DeferredRegister<Item> REGISTRY = DeferredRegister.create(Myrtrees.MOD_ID, Registry.ITEM_REGISTRY);

    default Supplier<Item> register(String name, Supplier<Item> item) {
        return REGISTRY.register(name, item);
    }

    Supplier<Item> RUBBERWOOD_LOG = REGISTRY.register("rubberwood_log", () -> new BlockItem(MyrtreesBlocks.RUBBERWOOD_LOG.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> STRIPPED_RUBBERWOOD_LOG = REGISTRY.register("stripped_rubberwood_log", () -> new BlockItem(MyrtreesBlocks.STRIPPED_RUBBERWOOD_LOG.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBERWOOD_PLANKS = REGISTRY.register("rubberwood_planks", () -> new BlockItem(MyrtreesBlocks.RUBBERWOOD_PLANKS.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBERWOOD_LEAVES = REGISTRY.register("rubberwood_leaves", () -> new BlockItem(MyrtreesBlocks.RUBBERWOOD_LEAVES.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBERWOOD_SAPLING = REGISTRY.register("rubberwood_sapling", () -> new BlockItem(MyrtreesBlocks.RUBBERWOOD_SAPLING.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> TREE_TAP = REGISTRY.register("tree_tap", () -> new BlockItem(MyrtreesBlocks.TREE_TAP.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", () -> new BlockItem(MyrtreesBlocks.WOODEN_BUCKET.get(), new Item.Properties().stacksTo(1).tab(Myrtrees.TAB)));
    Supplier<Item> LATEX = REGISTRY.register("latex", () -> new Item(new Item.Properties().tab(Myrtrees.TAB)));
}
