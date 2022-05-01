package io.alwa.mods.myrtrees.common;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class RubberTreeGeneration {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_REGISTRY = DeferredRegister.create(Myrtrees.MOD_ID, Registry.TRUNK_PLACER_TYPE_REGISTRY);

    // Register the tree feature
    private static final RegistrySupplier<TrunkPlacerType<?>> RUBBER_TREE_TRUNK_TYPE = TRUNK_REGISTRY.register("rubber_trunk_placer", () -> new TrunkPlacerType<>(RubberwoodTreeTrunkPlacer.CODEC));

    // Holders although they don't really need to be here as we don't use them anywhere else
    private static Holder<ConfiguredFeature<TreeConfiguration, ?>> RUBBER_TREE_CONFIGURED_FEATURE;
    private static Holder<PlacedFeature> RUBBER_TREE_PLACEMENT;

    /**
     * Called after all the registries are setup so we can jam in some new stuff
     */
    public static void initialize() {
        // Setups the feature, how it places, what logs to use, what leaves to use, how it places those leaves etc.
        RUBBER_TREE_CONFIGURED_FEATURE = FeatureUtils.register("rubber_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MyrtreesBlocks.RUBBERWOOD_LOG.get()),
                new RubberwoodTreeTrunkPlacer(5, 3, 0),
                BlockStateProvider.simple(MyrtreesBlocks.RUBBERWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().build());

        // No clue, copied the Oak tree placement, defines how the placement in the world and a load of checking predicates
        RUBBER_TREE_PLACEMENT = PlacementUtils.register(
                "rubber_tree_placement",
                RUBBER_TREE_CONFIGURED_FEATURE,
                PlacementUtils.countExtra(0, 0.05f, 1),
                InSquarePlacement.spread(),
                VegetationPlacements.TREE_THRESHOLD,
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(MyrtreesBlocks.RUBBERWOOD_SAPLING.get().defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome()
        );
    }

    /**
     * Actually adds the tree to the biomes feature properties based on a few predicates.
     * Needs to be a Forest, Swamp or Savanna unless the only jungle config is on, then it needs to be jungle
     */
    public static void biomeModifications(BiomeModifications.BiomeContext context, BiomeProperties.Mutable properties) {
        Biome.BiomeCategory c = context.getProperties().getCategory();

        if (c == Biome.BiomeCategory.JUNGLE || (!MyrtreesConfig.ONLY_JUNGLE && (c == Biome.BiomeCategory.FOREST || c == Biome.BiomeCategory.SWAMP || c == Biome.BiomeCategory.SAVANNA))) {
            properties.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RubberTreeGeneration.RUBBER_TREE_PLACEMENT);
        }
    }

    /**
     * Used for the Configured feature that defines how the trucks are placed. We use a custom one here to
     * define out own random height and to add a custom tile entity at the second to bottom truck.
     */
    private static class RubberwoodTreeTrunkPlacer extends TrunkPlacer {
        public static final Codec<RubberwoodTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> RubberwoodTreeTrunkPlacer.trunkPlacerParts(instance).apply(instance, RubberwoodTreeTrunkPlacer::new));

        public RubberwoodTreeTrunkPlacer(int x, int y, int z) {
            super(x, y, z);
        }

        @Override
        protected TrunkPlacerType<?> type() {
            return RUBBER_TREE_TRUNK_TYPE.get();
        }

        @Override
        public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedRW, BiConsumer<BlockPos, BlockState> biConsumer, Random random, int i, BlockPos blockPos, TreeConfiguration treeConfiguration) {
            setDirtAt(levelSimulatedRW, biConsumer, random, blockPos.below(), treeConfiguration);

            for (int j = 0; j < i; ++j) {
                if (j == 1) {
                    biConsumer.accept(blockPos.above(j), MyrtreesBlocks.FILLED_RUBBERWOOD_LOG.get().defaultBlockState());
                } else {
                    placeLog(levelSimulatedRW, biConsumer, random, blockPos.above(j), treeConfiguration);
                }
            }

            return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(i), 0, false));
        }
    }

    /**
     * Required for when a sapling is placed and grows, define the way the tree grows from the defined feature,
     * how many logs, how it builds the leaves etc.
     */
    public static class RubberWoodTree extends AbstractTreeGrower {
        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bl) {
            return RUBBER_TREE_CONFIGURED_FEATURE;
        }
    }
}
