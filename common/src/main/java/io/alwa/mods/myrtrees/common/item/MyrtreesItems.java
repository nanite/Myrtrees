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

    Supplier<Item> RUBBER_WOOD = REGISTRY.register("rubber_wood", () -> new BlockItem(MyrtreesBlocks.RUBBER_WOOD.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBER_PLANKS = REGISTRY.register("rubber_planks", () -> new BlockItem(MyrtreesBlocks.RUBBER_PLANKS.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBER_LEAVES = REGISTRY.register("rubber_leaves", () -> new BlockItem(MyrtreesBlocks.RUBBER_LEAVES.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> RUBBER_SAPLING = REGISTRY.register("rubber_sapling", () -> new BlockItem(MyrtreesBlocks.RUBBER_SAPLING.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> TREE_TAP = REGISTRY.register("tree_tap", () -> new BlockItem(MyrtreesBlocks.TREE_TAP.get(), new Item.Properties().tab(Myrtrees.TAB)));
    Supplier<Item> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", () -> new BlockItem(MyrtreesBlocks.WOODEN_BUCKET.get(), new Item.Properties().stacksTo(1).tab(Myrtrees.TAB)));
    Supplier<Item> LATEX = REGISTRY.register("latex", () -> new Item(new Item.Properties().tab(Myrtrees.TAB)));
}
