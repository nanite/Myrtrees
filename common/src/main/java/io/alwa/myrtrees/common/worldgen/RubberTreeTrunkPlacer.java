package io.alwa.myrtrees.common.worldgen;

import com.google.common.collect.ImmutableList;
import io.alwa.myrtrees.common.Myrtrees;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RubberTreeTrunkPlacer extends TrunkPlacer {
    public RubberTreeTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedRW levelSimulatedRW, Random random, int i, BlockPos blockPos, Set<BlockPos> set, BoundingBox boundingBox, TreeConfiguration treeConfiguration) {
        setDirtAt(levelSimulatedRW, blockPos.below());

        for (int j = 0; j < i; ++j) {
            if (j == 1) {
                levelSimulatedRW.setBlock(blockPos.above(j), Myrtrees.FILLED_RUBBER_WOOD.get().defaultBlockState(), 2);
            } else {
                placeLog(levelSimulatedRW, random, blockPos.above(j), set, boundingBox, treeConfiguration);
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(i), 0, false));
    }
}
