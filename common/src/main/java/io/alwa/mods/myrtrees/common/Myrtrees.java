package io.alwa.mods.myrtrees.common;

import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import io.alwa.mods.myrtrees.common.blockentity.MyrtreesBlockEntities;
import io.alwa.mods.myrtrees.common.item.MyrtreesItems;
import io.alwa.mods.myrtrees.common.mixin.MixinTrunkPlacerType;
import io.alwa.mods.myrtrees.common.worldgen.RubberwoodTreeGrower;
import io.alwa.mods.myrtrees.common.worldgen.RubberwoodTreeTrunkPlacer;
import me.shedaniel.architectury.hooks.biome.BiomeProperties;
import me.shedaniel.architectury.registry.BiomeModifications;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class Myrtrees {
    public static final String MOD_ID = "myrtrees";
    public static final CreativeModeTab TAB = CreativeTabs.create(new ResourceLocation(MOD_ID, "myrtrees_tab"), () -> new ItemStack(MyrtreesItems.RUBBERWOOD_SAPLING.get()));

    public static ConfiguredFeature<?, ?> RUBBER_TREE_FEATURE;
    public static TrunkPlacerType<RubberwoodTreeTrunkPlacer> RUBBER_TREE_TRUNK_PLACER;

    public static void init() {
        MyrtreesBlocks.REGISTRY.register();
        MyrtreesBlockEntities.REGISTRY.register();
        MyrtreesItems.REGISTRY.register();

        BiomeModifications.addProperties(Myrtrees::biomeModifications);
    }

    public static void afterRegistries() {
        RUBBER_TREE_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, "rubberwood_tree"), Feature.TREE.configured(RubberwoodTreeGrower.getRubberwoodTreeConfiguration()).chance(MyrtreesConfig.TREE_CHANCE).squared());
        RUBBER_TREE_TRUNK_PLACER = MixinTrunkPlacerType.callRegister("rubberwood_tree_placer", RubberwoodTreeTrunkPlacer.CODEC);
    }

    private static void biomeModifications(BiomeModifications.BiomeContext context, BiomeProperties.Mutable properties) {
        Biome.BiomeCategory c = context.getProperties().getCategory();

        if (c == Biome.BiomeCategory.JUNGLE || (!MyrtreesConfig.ONLY_JUNGLE && (c == Biome.BiomeCategory.FOREST || c == Biome.BiomeCategory.SWAMP || c == Biome.BiomeCategory.SAVANNA))) {
            properties.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RUBBER_TREE_FEATURE);
        }
    }

    public static void clientInit() {
        EnvExecutor.runInEnv(EnvType.CLIENT, () -> MyrtreesClient::init);
    }
}
