package io.github.orangekermit.kermittech.block.entity;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.custom.CoalGeneratorBlock;
import io.github.orangekermit.kermittech.screen.CoalGeneratorMenu;
import io.github.orangekermit.kermittech.util.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jline.utils.Log;

import java.io.Console;
import java.util.logging.Logger;

import static io.github.orangekermit.kermittech.block.custom.CoalGeneratorBlock.LIT;
import static net.minecraftforge.common.ForgeHooks.getBurnTime;

public class CoalGeneratorBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);

    private static final int INPUT_SLOT = 0;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int burnTime = 0;
    private int currentMaxBurnTime = 10;
    private int maxEnergyRate = 40;
    private int maxEnergyExtract = 80;
    private int energyRate = 0;
    private final CustomEnergyStorage energy = new CustomEnergyStorage(80000, 0, maxEnergyExtract, 0);
    private LazyOptional<CustomEnergyStorage> energyOptional = LazyOptional.of(() -> this.energy);

    public CoalGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COAL_GENERATOR_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> CoalGeneratorBlockEntity.this.burnTime;
                    case 1 -> CoalGeneratorBlockEntity.this.currentMaxBurnTime;
                    case 2 -> CoalGeneratorBlockEntity.this.energyRate;
                    case 3 -> CoalGeneratorBlockEntity.this.energy.getEnergyStored();
                    case 4 -> CoalGeneratorBlockEntity.this.energy.getMaxEnergyStored();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> CoalGeneratorBlockEntity.this.burnTime = pValue;
                    case 1 -> CoalGeneratorBlockEntity.this.currentMaxBurnTime = pValue;
                    case 2 -> CoalGeneratorBlockEntity.this.energyRate = pValue;
                    case 3 -> CoalGeneratorBlockEntity.this.energy.receiveEnergy(pValue, false);
                    case 4 -> CoalGeneratorBlockEntity.this.energy.getMaxEnergyStored();
                };
            }

            @Override
            public int getCount() {
                return 5;
            }
        };


    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }else if (cap == ForgeCapabilities.ENERGY) {
            return this.energyOptional.cast();
        }else{
            return super.getCapability(cap, side);
        }
    }

    public LazyOptional<CustomEnergyStorage> getEnergyOptional() {
        return this.energyOptional;
    }

    public CustomEnergyStorage getEnergy() {
        return this.energy;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        energyOptional = LazyOptional.of(() -> energy);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.kermittech.coal_generator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CoalGeneratorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("coal_generator.burnTime", burnTime);
        pTag.put("energy", this.energy.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        burnTime = pTag.getInt("coal_generator.burnTime");
        energy.deserializeNBT(pTag.get("energy"));
    }



    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(isBurning()) {
            decreaseBurnTime();
            increaseEnergy();
            setChanged(pLevel, pPos, pState);
        } else if (hasFuel()) {
            increaseBurnTime();
            removeFuel();
        }
        if(isBurning()){
            if(!pState.getValue(CoalGeneratorBlock.LIT)) {
                pState = pState.setValue(CoalGeneratorBlock.LIT, true);
                pLevel.setBlock(pPos, pState, 3);
            }
        }else{
            if(pState.getValue(CoalGeneratorBlock.LIT)) {
                pState = pState.setValue(CoalGeneratorBlock.LIT, false);
                pLevel.setBlock(pPos, pState, 3);
            }
            if(energyRate > 0){
                energyRate = 0;
            }
        }
        ejectEnergy();
    }

    private boolean hasFuel() {
        return getBurnTime(new ItemStack(this.itemHandler.getStackInSlot(INPUT_SLOT).getItem()), RecipeType.SMELTING) > 0;
    }

    private void increaseBurnTime() {
        int burnTimeDivisor = 1;
        currentMaxBurnTime = getBurnTime(new ItemStack(this.itemHandler.getStackInSlot(INPUT_SLOT).getItem()), RecipeType.SMELTING)/ burnTimeDivisor;
        burnTime = currentMaxBurnTime;
    }

    private void removeFuel() {
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);;
    }

    private void increaseEnergy() {
        if(this.energy.getEnergyStored() < this.energy.getMaxEnergyStored()) {
            this.energyRate = maxEnergyRate;
            this.energy.addEnergy(energyRate);
        }
    }

    private void ejectEnergy() {
        if(energy.canExtract()){
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = this.worldPosition.relative(direction);
                assert level != null;
                final BlockEntity adjacentBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (adjacentBlockEntity == null) {
                    continue;
                }
                adjacentBlockEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    final int toSend = this.energy.extractEnergy(this.maxEnergyExtract, false);
                    final int received = storage.receiveEnergy(toSend, false);
                    this.energy.setEnergy(this.energy.getEnergyStored() + toSend - received);
                });
            }
        }
    }

    private void decreaseBurnTime() {
        burnTime--;
    }

    private boolean isBurning() {
        return (burnTime > 0);
    }
}
