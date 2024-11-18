package io.github.orangekermit.kermittech.block.entity;

import io.github.orangekermit.kermittech.util.CustomEnergyStorage;
import io.github.orangekermit.kermittech.util.BlockInteractionItemStackHandler;
import io.github.orangekermit.kermittech.util.PlayerInteractionItemStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AbstractMachineBlockEntity extends BlockEntity {
    protected final PlayerInteractionItemStackHandler itemHandler;
    protected final BlockInteractionItemStackHandler blockItemHandler;
    protected final RecipeType<?> recipeType;
    protected int maxEnergyRate; // Max energy generated or consumed
    protected int maxEnergyExtract;
    protected int maxEnergyInsert;
    protected int energyRate = 0;
    protected int energyCapacity;
    protected int processProgress = 0;
    protected int maxProcessProgress;
    private LazyOptional<IItemHandler> lazyItemHandler;
    private LazyOptional<IItemHandler> lazyBlockItemHandler;
    protected final CustomEnergyStorage energy;
    protected LazyOptional<CustomEnergyStorage> energyOptional;

    public AbstractMachineBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState,
                                      int itemHandlerSize,
                                      int maxEnergyRate,
                                      int maxEnergyExtract,
                                      int maxEnergyInsert,
                                      int energyCapacity,
                                      int maxProcessProgress,
                                      RecipeType<?> recipeType) {
        super(pType, pPos, pBlockState);
        this.itemHandler = new PlayerInteractionItemStackHandler(itemHandlerSize);
        this.blockItemHandler = new BlockInteractionItemStackHandler(this.itemHandler);
        this.maxProcessProgress = maxProcessProgress;
        this.maxEnergyRate = maxEnergyRate;
        this.maxEnergyExtract = maxEnergyExtract;
        this.maxEnergyInsert = maxEnergyInsert;
        this.energyCapacity = energyCapacity;
        this.lazyItemHandler = LazyOptional.empty();
        this.lazyBlockItemHandler = LazyOptional.empty();
        this.energy = new CustomEnergyStorage(energyCapacity, maxEnergyInsert, maxEnergyExtract, 0);
        this.energyOptional = LazyOptional.of(() -> this.energy);
        this.recipeType = recipeType;

    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side != null) {
                return lazyBlockItemHandler.cast();
            }else{
                return lazyItemHandler.cast();
            }
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
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyBlockItemHandler.invalidate();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyBlockItemHandler = LazyOptional.of(() -> blockItemHandler);
        energyOptional = LazyOptional.of(() -> energy);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    protected void ejectEnergy() {
        if(energy.canExtract()){
            for (Direction direction : Direction.values()) {
                assert level != null;
                final BlockEntity adjacentBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (adjacentBlockEntity == null) {
                    continue;
                }
                adjacentBlockEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    final int toSend = this.energy.extractEnergy(this.maxEnergyExtract, false);
                    final int received = storage.receiveEnergy(toSend, false);
                    this.energy.addEnergy(toSend - received);
                });
            }
        }
    }

    protected void receiveEnergy() {
        if(energy.canReceive()){
            for (Direction direction : Direction.values()) {
                assert level != null;
                final BlockEntity adjacentBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (adjacentBlockEntity == null) {
                    continue;
                }
                adjacentBlockEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    final int toReceive = this.energy.receiveEnergy(this.maxEnergyInsert, false);
                    final int sent = storage.extractEnergy(toReceive, false);
                    this.energy.addEnergy(toReceive - sent);
                });
            }
        }
    }
}
