package io.alwa.mods.myrtrees.common.block;

import io.alwa.mods.myrtrees.common.MyrtreesConfig;
import io.alwa.mods.myrtrees.common.blockentity.WoodenBucketBlockEntity;
import io.alwa.mods.myrtrees.common.item.MyrtreesItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WoodenBucketBlock extends Block implements EntityBlock {
    private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 11, 15);

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public WoodenBucketBlock() {
        super(Properties.copy(Blocks.JUNGLE_WOOD));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private boolean canAttachTo(BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return blockGetter.getBlockState(blockPos).getBlock() instanceof RubberwoodLogBlock || blockGetter.getBlockState(blockPos).getBlock() instanceof FilledRubberwoodLogBlock;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return !this.canSurvive(blockState, levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING);
        return this.canAttachTo(levelReader, blockPos.relative(direction.getOpposite()), direction);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof WoodenBucketBlockEntity) {
            WoodenBucketBlockEntity bucket = (WoodenBucketBlockEntity) blockEntity;
            if (!level.isClientSide && player.isCreative()) {
                ItemStack itemStack = new ItemStack(MyrtreesItems.WOODEN_BUCKET.get());

                if (bucket.latex > 0) {
                    CompoundTag compoundTag = new CompoundTag();
                    compoundTag.putInt("latex", bucket.latex);
                    if (!compoundTag.isEmpty()) {
                        itemStack.addTagElement("BlockEntityTag", compoundTag);
                    }
                }

                ItemEntity itemEntity = new ItemEntity(level, (double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, itemStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter blockGetter) {
        return new WoodenBucketBlockEntity();
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        BlockEntity entity = level.getBlockEntity(pos);

        if (entity instanceof WoodenBucketBlockEntity) {
            WoodenBucketBlockEntity e = (WoodenBucketBlockEntity) entity;

            if (e.latex >= MyrtreesConfig.LATEX_FOR_ITEM) {
                e.latex -= MyrtreesConfig.LATEX_FOR_ITEM;

                if (!level.isClientSide()) {
                    popResource(level, pos.above(), new ItemStack(MyrtreesItems.LATEX.get()));
                    e.setChanged();
                }
            }

            if (!level.isClientSide()) {
                player.displayClientMessage(new TextComponent(String.format("%,d / %,d", e.latex, MyrtreesConfig.BUCKET_CAPACITY)), true);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
        if (stack.hasTag() && stack.getTag().contains("BlockEntityTag")) {
            list.add(new TextComponent(String.format("%,d / %,d", stack.getTag().getCompound("BlockEntityTag").getInt("latex"), MyrtreesConfig.BUCKET_CAPACITY)).withStyle(ChatFormatting.GRAY));
        }
    }
}
