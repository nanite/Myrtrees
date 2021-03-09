package io.alwa.myrtrees.common;

import io.alwa.myrtrees.common.block.*;
import io.alwa.myrtrees.common.blockentity.RubberWoodBlockEntity;
import io.alwa.myrtrees.common.blockentity.TreepTapBlockEntity;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.architectury.registry.RegistrySupplier;
import me.shedaniel.architectury.registry.RenderTypes;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class Myrtrees {
    public static final String MOD_ID = "myrtrees";
    public static final CreativeModeTab MYRTREES_TAB = CreativeTabs.create(new ResourceLocation(MOD_ID, "myrtrees_tab"), new Supplier<ItemStack>() {
        @Override
        public ItemStack get() {
            return new ItemStack(RUBBER_SAPLING.get());
        }
    });

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_REGISTRY);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<Block> RUBBER_WOOD = BLOCKS.register("rubber_wood", RubberWoodBlock::new);
    public static final RegistrySupplier<Block> RUBBER_PLANKS = BLOCKS.register("rubber_planks", RubberPlanksBlock::new);
    public static final RegistrySupplier<Block> RUBBER_LEAVES = BLOCKS.register("rubber_leaves", RubberLeavesBlock::new);
    public static final RegistrySupplier<Block> RUBBER_SAPLING = BLOCKS.register("rubber_sapling", RubberSaplingBlock::new);
    public static final RegistrySupplier<Block> FILLED_RUBBER_WOOD = BLOCKS.register("filled_rubber_wood", FilledRubberWoodBlock::new);
    public static final RegistrySupplier<Block> TREE_TAP = BLOCKS.register("tree_tap", TreeTapBlock::new);
    public static final RegistrySupplier<Block> WOODEN_BUCKET = BLOCKS.register("wooden_bucket", WoodenBucketBlock::new);

    public static final RegistrySupplier<BlockEntityType> RUBBER_WOOD_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("rubber_wood", () -> BlockEntityType.Builder.of(RubberWoodBlockEntity::new, RUBBER_WOOD.get()).build(null));
    public static final RegistrySupplier<BlockEntityType> TREE_TAP_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("tree_tap", () -> BlockEntityType.Builder.of(TreepTapBlockEntity::new, TREE_TAP.get()).build(null));

    public static final RegistrySupplier<Item> RUBBER_WOOD_ITEM = ITEMS.register("rubber_wood", () -> new BlockItem(RUBBER_WOOD.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));
    public static final RegistrySupplier<Item> RUBBER_PLANKS_ITEM = ITEMS.register("rubber_planks", () -> new BlockItem(RUBBER_PLANKS.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));
    public static final RegistrySupplier<Item> RUBBER_LEAVES_ITEM = ITEMS.register("rubber_leaves", () -> new BlockItem(RUBBER_LEAVES.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));
    public static final RegistrySupplier<Item> RUBBER_SAPLING_ITEM = ITEMS.register("rubber_sapling", () -> new BlockItem(RUBBER_SAPLING.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));
    public static final RegistrySupplier<Item> TREE_TAP_ITEM = ITEMS.register("tree_tap", () -> new BlockItem(TREE_TAP.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));
    public static final RegistrySupplier<Item> WOODEN_BUCKET_ITEM = ITEMS.register("wooden_bucket", () -> new BlockItem(WOODEN_BUCKET.get(), new Item.Properties().tab(Myrtrees.MYRTREES_TAB)));

    public static void init() {
        BLOCKS.register();
        BLOCK_ENTITY_TYPES.register();
        ITEMS.register();
    }

    public static void clientInit() {
        EnvExecutor.runInEnv(EnvType.CLIENT, () -> () -> RenderTypes.register(RenderType.cutout(), RUBBER_SAPLING.get()));
    }
}
