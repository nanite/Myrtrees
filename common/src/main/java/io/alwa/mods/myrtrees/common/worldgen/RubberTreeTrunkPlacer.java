package io.alwa.mods.myrtrees.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.alwa.mods.myrtrees.common.Myrtrees;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
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
    public static final Codec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> trunkPlacerParts(instance).apply(instance, RubberTreeTrunkPlacer::new));

    public RubberTreeTrunkPlacer(int x, int y, int z) {
        super(x, y, z);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return Myrtrees.RUBBER_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedRW levelSimulatedRW, Random random, int i, BlockPos blockPos, Set<BlockPos> set, BoundingBox boundingBox, TreeConfiguration treeConfiguration) {
        setDirtAt(levelSimulatedRW, blockPos.below());

        for (int j = 0; j < i; ++j) {
            if (j == 1) {
                levelSimulatedRW.setBlock(blockPos.above(j), MyrtreesBlocks.FILLED_RUBBER_WOOD.get().defaultBlockState(), 2);
            } else {
                placeLog(levelSimulatedRW, random, blockPos.above(j), set, boundingBox, treeConfiguration);
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(i), 0, false));
    }
}
