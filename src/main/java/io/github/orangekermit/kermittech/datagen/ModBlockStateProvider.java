package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.CheckReturnValue;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, KermitTech.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALUMINIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_ALUMINIUM_BLOCK);

        blockWithItem(ModBlocks.ALUMINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);

        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.RAW_TIN_BLOCK);

        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TIN_ORE);

        //orientableBlockWithItem(ModBlocks.COAL_GENERATOR);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void orientableBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        ResourceLocation side = new ResourceLocation(KermitTech.MOD_ID,"block/" + blockRegistryObject.getId().getPath() + "_side");
        ResourceLocation front = new ResourceLocation(KermitTech.MOD_ID,"block/" + blockRegistryObject.getId().getPath() + "_front");
        ResourceLocation top = new ResourceLocation(KermitTech.MOD_ID,"block/" + blockRegistryObject.getId().getPath() + "_top");
        //simpleBlockWithItem(blockRegistryObject.get(), models().orientableWithBottom("coal_generator", side, front, top, top));
        ModelFile model = models().orientable("coal_generator", side, front, top);

        simpleBlock(blockRegistryObject.get(),
                ConfiguredModel.allYRotations(model,0, false));
        simpleBlockItem(blockRegistryObject.get(), model);
    }
}