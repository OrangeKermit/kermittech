package io.github.orangekermit.kermittech.block.entity;

import io.github.orangekermit.kermittech.block.custom.CoalGeneratorBlock;
import io.github.orangekermit.kermittech.block.custom.ElectricalFurnaceBlock;
import io.github.orangekermit.kermittech.screen.ElectricalFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jline.utils.Log;

import java.util.Optional;


public class ElectricalFurnaceBlockEntity extends AbstractMachineBlockEntity implements MenuProvider {
    protected final ContainerData data;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    public ElectricalFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ELECTRICAL_FURNACE_BE.get(), pPos, pBlockState,
                2, 40, 0, 160, 40000, 100, RecipeType.SMELTING);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> ElectricalFurnaceBlockEntity.this.energy.getEnergyStored();
                    case 1 -> ElectricalFurnaceBlockEntity.this.energy.getMaxEnergyStored();
                    case 2 -> ElectricalFurnaceBlockEntity.this.energyRate;
                    case 3 -> ElectricalFurnaceBlockEntity.this.processProgress;
                    case 4 -> ElectricalFurnaceBlockEntity.this.maxProcessProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> ElectricalFurnaceBlockEntity.this.energy.receiveEnergy(pValue, false);
                    case 1 -> ElectricalFurnaceBlockEntity.this.energy.getMaxEnergyStored();
                    case 2 -> ElectricalFurnaceBlockEntity.this.energyRate = pValue;
                    case 3 -> ElectricalFurnaceBlockEntity.this.processProgress = pValue;
                    case 4 -> ElectricalFurnaceBlockEntity.this.maxProcessProgress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 5;
            }
        };
        this.itemHandler.setSlotAsInput(INPUT_SLOT);
        this.itemHandler.setSlotAsOutput(OUTPUT_SLOT);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.kermittech.electrical_furnace");
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ElectricalFurnaceMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("electrical_furnace.processProgress", processProgress);
        pTag.put("energy", this.energy.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        processProgress = pTag.getInt("electrical_furnace.processProgress");
        energy.deserializeNBT(pTag.get("energy"));
    }

    private void craftItem(){
        Optional<SmeltingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        Optional<SmeltingRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<SmeltingRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item){
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count){
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe() && hasEnergy()){
            this.processProgress += 1;
            this.energyRate = this.maxEnergyRate;
            decreaseEnergy();
            if(!pState.getValue(ElectricalFurnaceBlock.LIT)){
                pState = pState.setValue(ElectricalFurnaceBlock.LIT, true);
                pLevel.setBlock(pPos, pState, 3);
            }
            if(processComplete()){
                this.processProgress = 0;
                craftItem();
            }
            setChanged(pLevel, pPos, pState);
        }else if(pState.getValue(ElectricalFurnaceBlock.LIT)){
            this.energyRate = 0;
            pState = pState.setValue(ElectricalFurnaceBlock.LIT, false);
            pLevel.setBlock(pPos, pState, 3);
        }
        if(!hasRecipe()){
            this.processProgress = 0;
        }
    }

    private boolean processComplete(){
        return processProgress >= maxProcessProgress;
    }

    private boolean hasEnergy(){
        return getEnergy().getEnergyStored() >= maxEnergyRate;
    }

    private void decreaseEnergy(){
        getEnergy().removeEnergy(energyRate);
    }

}
