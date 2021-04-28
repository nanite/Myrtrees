package io.alwa.myrtrees.common.blockentity;

import io.alwa.myrtrees.common.Myrtrees;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TickableBlockEntity;

public class DryingPlateBlockEntity extends BlockEntity implements TickableBlockEntity {

    private int CONVERSION_RATE = 200;
    private boolean hasLatex = false;

    public DryingPlateBlockEntity() {
        super(Myrtrees.DRYING_PLATE_ENTITY_TYPE.get());
    }

    @Override
    public void tick() {
        if (hasLatex) {
            // Do something here to decide if it's dry or not
            setHasLatex(false);
            Block.popResource(level, worldPosition, new ItemStack(Myrtrees.RUBBER_ITEM.get()));
        }
    }

    public boolean hasLatex() {
        return hasLatex;
    }

    public void setHasLatex(boolean hasLatex) {
        this.hasLatex = hasLatex;
    }
}
