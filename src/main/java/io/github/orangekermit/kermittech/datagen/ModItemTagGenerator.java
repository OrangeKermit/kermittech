package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import io.github.orangekermit.kermittech.item.ModItems;
import io.github.orangekermit.kermittech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
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
        // GENERAL TAGS
        this.tag(ModTags.ForgeItems.INGOT)
                .add(ModItems.ALUMINIUM_INGOT.get());
        this.tag(ModTags.ForgeItems.NUGGET)
                .add(ModItems.ALUMINIUM_NUGGET.get());

        // SPECIFIC TAGS
        this.tag(ModTags.ForgeItems.ALUMINIUM_INGOT)
                .add(ModItems.ALUMINIUM_INGOT.get());
        this.tag(ModTags.ForgeItems.ALUMINIUM_NUGGET)
                .add(ModItems.ALUMINIUM_NUGGET.get());
        this.tag(ModTags.ForgeItems.RAW_ALUMINIUM)
                .add(ModItems.RAW_ALUMINIUM.get());
    }
}