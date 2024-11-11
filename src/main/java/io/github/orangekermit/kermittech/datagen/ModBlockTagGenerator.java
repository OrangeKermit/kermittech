package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import io.github.orangekermit.kermittech.item.ModItems;
import io.github.orangekermit.kermittech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KermitTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // MINING TAGS
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                        ModBlocks.ALUMINIUM_BLOCK.get(),
                        ModBlocks.ALUMINIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                        ModBlocks.COAL_GENERATOR.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                        ModBlocks.ALUMINIUM_BLOCK.get(),
                        ModBlocks.ALUMINIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

        // GENERAL TAGS
        this.tag(ModTags.ForgeBlocks.ORE)
                .add(ModBlocks.ALUMINIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

        this.tag(ModTags.ForgeBlocks.STORAGE_BLOCK)
                .add(ModBlocks.ALUMINIUM_BLOCK.get());

        // SPECIFIC TAGS
        this.tag(ModTags.ForgeBlocks.ALUMINIUM_ORE)
                .add(ModBlocks.ALUMINIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

        this.tag(ModTags.ForgeBlocks.ALUMINIUM_BLOCK)
                .add(ModBlocks.ALUMINIUM_BLOCK.get());
    }
}