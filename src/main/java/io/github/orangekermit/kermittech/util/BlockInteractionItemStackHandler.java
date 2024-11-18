package io.github.orangekermit.kermittech.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlockInteractionItemStackHandler implements IItemHandler {
    private final PlayerInteractionItemStackHandler baseHandler;

    public BlockInteractionItemStackHandler(PlayerInteractionItemStackHandler baseHandler) {
        this.baseHandler = baseHandler;
    }

    @Override
    public int getSlots() {
        return baseHandler.getSlots();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return baseHandler.getStackInSlot(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if(this.baseHandler.outputSlots.contains(slot)){
            return stack;
        }
        return baseHandler.insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(this.baseHandler.inputSlots.contains(slot)){
            return ItemStack.EMPTY;
        }
        return baseHandler.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return baseHandler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack itemStack) {
        return baseHandler.isItemValid(slot, itemStack);
    }
}
