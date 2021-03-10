package io.alwa.myrtrees.common.worldgen;

import io.alwa.myrtrees.common.Myrtrees;
import net.minecraft.util.UniformInt;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RubberTreeGrower extends AbstractTreeGrower {

    public static TreeConfiguration getRubberTreeConfiguration() {
        return (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(Myrtrees.RUBBER_WOOD.get().defaultBlockState()), new SimpleStateProvider(Myrtrees.RUBBER_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(0), 3), new RubberTreeTrunkPlacer(5, 3, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean bl) {
        return Feature.TREE.configured(getRubberTreeConfiguration());
    }
}
