package io.alwa.mods.myrtrees.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.alwa.mods.myrtrees.common.Myrtrees;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class RubberwoodTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<RubberwoodTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> trunkPlacerParts(instance).apply(instance, RubberwoodTreeTrunkPlacer::new));

    public RubberwoodTreeTrunkPlacer(int x, int y, int z) {
        super(x, y, z);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return Myrtrees.RUBBER_TREE_TRUNK_PLACER;
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
