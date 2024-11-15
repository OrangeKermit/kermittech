package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import io.github.orangekermit.kermittech.item.ModItems;
import io.github.orangekermit.kermittech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, KermitTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ALUMINIUM_HELMET.get(),
                        ModItems.ALUMINIUM_CHESTPLATE.get(),
                        ModItems.ALUMINIUM_LEGGINGS.get(),
                        ModItems.ALUMINIUM_BOOTS.get());

        // GENERAL TAGS
        this.tag(ModTags.ForgeItems.INGOT)
                .add(ModItems.ALUMINIUM_INGOT.get())
                .add(ModItems.TIN_INGOT.get());
        this.tag(ModTags.ForgeItems.RAW_MATERIALS)
                .add(ModItems.RAW_ALUMINIUM.get())
                .add(ModItems.RAW_TIN.get());
        this.tag(ModTags.ForgeItems.NUGGET)
                .add(ModItems.ALUMINIUM_NUGGET.get())
                .add(ModItems.TIN_NUGGET.get());

        this.tag(ModTags.ForgeItems.STORAGE_BLOCKS)
                .add(ModBlocks.ALUMINIUM_BLOCK.get().asItem(),
                        ModBlocks.RAW_ALUMINIUM_BLOCK.get().asItem(),
                        ModBlocks.TIN_BLOCK.get().asItem(),
                        ModBlocks.RAW_TIN_BLOCK.get().asItem());
        this.tag(ModTags.ForgeItems.ORES)
                .add(ModBlocks.ALUMINIUM_ORE.get().asItem(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().asItem());

        // SPECIFIC TAGS
        this.tag(ModTags.ForgeItems.ALUMINIUM_INGOT)
                .add(ModItems.ALUMINIUM_INGOT.get());
        this.tag(ModTags.ForgeItems.ALUMINIUM_NUGGET)
                .add(ModItems.ALUMINIUM_NUGGET.get());
        this.tag(ModTags.ForgeItems.RAW_ALUMINIUM)
                .add(ModItems.RAW_ALUMINIUM.get());

        this.tag(ModTags.ForgeItems.TIN_INGOT)
                .add(ModItems.TIN_INGOT.get());
        this.tag(ModTags.ForgeItems.TIN_NUGGET)
                .add(ModItems.TIN_NUGGET.get());
        this.tag(ModTags.ForgeItems.RAW_TIN)
                .add(ModItems.RAW_TIN.get());

        this.tag(ModTags.ForgeItems.RAW_ALUMINIUM_BLOCK)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get().asItem());
        this.tag(ModTags.ForgeItems.ALUMINIUM_BLOCK)
                .add(ModBlocks.ALUMINIUM_BLOCK.get().asItem());
        this.tag(ModTags.ForgeItems.RAW_TIN_BLOCK)
                .add(ModBlocks.RAW_TIN_BLOCK.get().asItem());
        this.tag(ModTags.ForgeItems.TIN_BLOCK)
                .add(ModBlocks.TIN_BLOCK.get().asItem());

        this.tag(ModTags.ForgeItems.ALUMINIUM_ORE)
                .add(ModBlocks.ALUMINIUM_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().asItem());
        this.tag(ModTags.ForgeItems.TIN_ORE)
                .add(ModBlocks.TIN_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get().asItem());
    }
}