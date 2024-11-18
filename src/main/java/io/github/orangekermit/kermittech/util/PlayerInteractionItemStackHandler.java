package io.github.orangekermit.kermittech.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteractionItemStackHandler extends ItemStackHandler {
    protected List<Integer> outputSlots = new ArrayList<Integer>();
    protected List<Integer> inputSlots = new ArrayList<Integer>();

    public PlayerInteractionItemStackHandler(int itemHandlerSize) {
        super(itemHandlerSize);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return super.extractItem(slot, amount, simulate);
    }

    public void setSlotAsOutput(int outputSlot) {
        this.outputSlots.add(outputSlot);
    }

    public void setSlotAsInput(int inputSlot) {
        this.inputSlots.add(inputSlot);
    }
}
