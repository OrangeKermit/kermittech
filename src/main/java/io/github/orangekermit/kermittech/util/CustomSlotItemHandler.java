package io.github.orangekermit.kermittech.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class CustomSlotItemHandler extends SlotItemHandler {
    protected boolean mayPlace;
    protected boolean mayPickup;
    public CustomSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, boolean mayPlace, boolean mayPickup) {
        super(itemHandler, index, xPosition, yPosition);
        setMayPlace(mayPlace);
        setMayPickup(mayPickup);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return mayPlace;
    }

    public void setMayPlace(boolean mayPlace) {
        this.mayPlace = mayPlace;
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return mayPickup;
    }

    public void setMayPickup(boolean mayPickup) {
        this.mayPickup = mayPickup;
    }
}
