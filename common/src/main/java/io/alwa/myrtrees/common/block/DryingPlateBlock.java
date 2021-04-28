package io.alwa.myrtrees.common.block;

import io.alwa.myrtrees.common.Myrtrees;
import io.alwa.myrtrees.common.blockentity.DryingPlateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class DryingPlateBlock extends Block implements EntityBlock {

    private int CONVERSION_AMOUNT = 200;

    public DryingPlateBlock() {
        super(Properties.copy(Blocks.HOPPER));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new DryingPlateBlockEntity();
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack handItem = player.getItemInHand(interactionHand);
        if (handItem.getItem() == Myrtrees.WOODEN_BUCKET_ITEM.get()) {

            BlockEntity blockEntity = level.getBlockEntity(blockPos);

            if (blockEntity instanceof DryingPlateBlockEntity) {
                // Do a check here to see if the bucket has enough latex once we can pick them up
                if (!level.isClientSide) {
                    int currentLatex = handItem.getTagElement("BlockEntityTag").getInt("latex");
                    System.out.println("Current Latex: " + currentLatex);
                    if (currentLatex >= CONVERSION_AMOUNT) {
                        if (!((DryingPlateBlockEntity) blockEntity).hasLatex()) {
                            System.out.println("Setting has latex to true");
                            ((DryingPlateBlockEntity) blockEntity).setHasLatex(true);
                            System.out.println("Setting Latex: " + (currentLatex - CONVERSION_AMOUNT));
                            handItem.getTagElement("BlockEntityTag").putInt("latex", currentLatex - CONVERSION_AMOUNT);
                        }
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
