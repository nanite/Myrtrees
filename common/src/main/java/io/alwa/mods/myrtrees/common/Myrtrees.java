package io.alwa.mods.myrtrees.common;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.utils.EnvExecutor;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import io.alwa.mods.myrtrees.common.blockentity.MyrtreesBlockEntities;
import io.alwa.mods.myrtrees.common.item.MyrtreesItems;
import net.fabricmc.api.EnvType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Myrtrees {
    public static final String MOD_ID = "myrtrees";
    public static final CreativeModeTab TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "myrtrees_tab"), () -> new ItemStack(MyrtreesItems.RUBBERWOOD_SAPLING.get()));

    public static void init() {
        MyrtreesBlocks.REGISTRY.register();
        MyrtreesBlockEntities.REGISTRY.register();
        MyrtreesItems.REGISTRY.register();

        RubberTreeGeneration.TRUNK_REGISTRY.register();
        BiomeModifications.addProperties(RubberTreeGeneration::biomeModifications);
    }

    public static void afterRegistries() {
        RubberTreeGeneration.initialize();
        FuelRegistry.register(300, MyrtreesItems.RUBBERWOOD_LOG.get(), MyrtreesItems.RUBBERWOOD_PLANKS.get());
    }

    public static void clientInit() {
        EnvExecutor.runInEnv(EnvType.CLIENT, () -> MyrtreesClient::init);
    }
}
