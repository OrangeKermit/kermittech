package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import io.github.orangekermit.kermittech.item.ModItems;
import io.github.orangekermit.kermittech.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ALUMINIUM_SMELTABLES = List.of(ModItems.RAW_ALUMINIUM.get(),
            ModBlocks.ALUMINIUM_ORE.get(), ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
    private static final List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN.get(),
            ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium_ingot");
        oreBlasting(pWriter, ALUMINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium_ingot");
        oreSmelting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin_ingot");
        oreBlasting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 100, "tin_ingot");

        // ALUMINIUM
        genericOre("aluminium",
                ModItems.ALUMINIUM_INGOT.get(),
                ModTags.ForgeItems.ALUMINIUM_INGOT,
                ModItems.ALUMINIUM_NUGGET.get(),
                ModTags.ForgeItems.ALUMINIUM_NUGGET,
                ModBlocks.ALUMINIUM_BLOCK.get(),
                ModTags.ForgeItems.ALUMINIUM_BLOCK,
                ModItems.RAW_ALUMINIUM.get(),
                ModTags.ForgeItems.RAW_ALUMINIUM,
                ModBlocks.RAW_ALUMINIUM_BLOCK.get(),
                ModTags.ForgeItems.RAW_ALUMINIUM_BLOCK,
                pWriter);

        // ALUMINIUM GEAR
        fullGear(ModItems.ALUMINIUM_INGOT.get(),
                ModItems.ALUMINIUM_HELMET.get(),
                ModItems.ALUMINIUM_CHESTPLATE.get(),
                ModItems.ALUMINIUM_LEGGINGS.get(),
                ModItems.ALUMINIUM_BOOTS.get(),
                ModItems.ALUMINIUM_SWORD.get(),
                ModItems.ALUMINIUM_PICKAXE.get(),
                ModItems.ALUMINIUM_AXE.get(),
                ModItems.ALUMINIUM_SHOVEL.get(),
                ModItems.ALUMINIUM_HOE.get(), pWriter);

        // TIN
        genericOre("tin",
                ModItems.TIN_INGOT.get(),
                ModTags.ForgeItems.TIN_INGOT,
                ModItems.TIN_NUGGET.get(),
                ModTags.ForgeItems.TIN_NUGGET,
                ModBlocks.TIN_BLOCK.get(),
                ModTags.ForgeItems.TIN_BLOCK,
                ModItems.RAW_TIN.get(),
                ModTags.ForgeItems.RAW_TIN,
                ModBlocks.RAW_TIN_BLOCK.get(),
                ModTags.ForgeItems.RAW_TIN_BLOCK,
                pWriter);

        // TIN GEAR
        fullGear(ModItems.TIN_INGOT.get(),
                ModItems.TIN_HELMET.get(),
                ModItems.TIN_CHESTPLATE.get(),
                ModItems.TIN_LEGGINGS.get(),
                ModItems.TIN_BOOTS.get(),
                ModItems.TIN_SWORD.get(),
                ModItems.TIN_PICKAXE.get(),
                ModItems.TIN_AXE.get(),
                ModItems.TIN_SHOVEL.get(),
                ModItems.TIN_HOE.get(), pWriter);

        // COILS
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_COIL.get())
                .pattern(" NA")
                .pattern("CRN")
                .pattern("AC ")
                .define('A', ModTags.ForgeItems.ALUMINIUM_INGOT)
                .define('N', ModTags.ForgeItems.ALUMINIUM_NUGGET)
                .define('C',  Tags.Items.INGOTS_COPPER)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(getHasName(ModItems.ALUMINIUM_INGOT.get()), has(ModItems.ALUMINIUM_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIN_COIL.get())
                .pattern(" NA")
                .pattern("CRN")
                .pattern("AC ")
                .define('A', ModTags.ForgeItems.TIN_INGOT)
                .define('N', ModTags.ForgeItems.TIN_NUGGET)
                .define('C',  Tags.Items.INGOTS_COPPER)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get()))
                .save(pWriter);

        // MACHINES
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MACHINE_FRAME.get())
                .pattern("III")
                .pattern("TAT")
                .pattern("III")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('T', ModTags.ForgeItems.TIN_INGOT)
                .define('A', ModTags.ForgeItems.ALUMINIUM_INGOT)
                .unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELECTRICAL_FURNACE.get())
                .pattern("CFC")
                .pattern("AMA")
                .pattern("III")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('F', Items.FURNACE)
                .define('C', ModItems.TIN_COIL.get())
                .define('A', ModTags.ForgeItems.ALUMINIUM_INGOT)
                .define('M', ModBlocks.MACHINE_FRAME.get().asItem())
                .unlockedBy(getHasName(ModItems.TIN_COIL.get()), has(ModItems.TIN_COIL.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COAL_GENERATOR.get())
                .pattern("QAQ")
                .pattern("CFC")
                .pattern("SBS")
                .define('A', ModTags.ForgeItems.ALUMINIUM_INGOT)
                .define('Q', ModItems.ALUMINIUM_COIL.get())
                .define('F', Items.FURNACE)
                .define('C',  Tags.Items.INGOTS_COPPER)
                .define('S', Tags.Items.COBBLESTONE)
                .define('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .unlockedBy(getHasName(ModItems.ALUMINIUM_COIL.get()), has(ModItems.ALUMINIUM_COIL.get()))
                .save(pWriter);

    }

    protected static void genericOre(String name, ItemLike ingot, TagKey<Item> ingot_tag, ItemLike nugget, TagKey<Item> nugget_tag, ItemLike block, TagKey<Item> block_tag,
                                     ItemLike raw_ore, TagKey<Item> raw_ore_tag, ItemLike raw_ore_block, TagKey<Item> raw_ore_block_tag, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingot_tag)
                .unlockedBy(getHasName(ingot), has(ingot_tag))
                .save(pWriter, name+"_block_from_"+name+"_ingot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block_tag)
                .unlockedBy(getHasName(block), has(block_tag))
                .save(pWriter, name+"_ingot_from_"+name+"_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, raw_ore_block)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', raw_ore_tag)
                .unlockedBy(getHasName(raw_ore), has(raw_ore_tag))
                .save(pWriter, "raw_"+name+"_block_from_raw_"+name);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, raw_ore, 9)
                .requires(raw_ore_block_tag)
                .unlockedBy(getHasName(raw_ore_block), has(raw_ore_block_tag))
                .save(pWriter, "raw_"+name+"_from_raw_"+name+"_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', nugget_tag)
                .unlockedBy(getHasName(nugget), has(nugget_tag))
                .save(pWriter, name+"_ingot_from_"+name+"_nugget");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
                .requires(ingot_tag)
                .unlockedBy(getHasName(ingot), has(ingot_tag))
                .save(pWriter, name+"_nugget_from_"+name+"_ingot");
    }

    protected static void fullGear(ItemLike ingot, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots,
            ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, helmet)
                .pattern("SSS")
                .pattern("S S")
                .pattern("   ")
                .define('S', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, chestplate)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, leggings)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, boots)
                .pattern("   ")
                .pattern("S S")
                .pattern("S S")
                .define('S', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sword)
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" I ")
                .define('S', ingot)
                .define('I', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pickaxe)
                .pattern("SSS")
                .pattern(" I ")
                .pattern(" I ")
                .define('S', ingot)
                .define('I', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, axe)
                .pattern(" SS")
                .pattern(" IS")
                .pattern(" I ")
                .define('S', ingot)
                .define('I', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, shovel)
                .pattern(" S ")
                .pattern(" I ")
                .pattern(" I ")
                .define('S', ingot)
                .define('I', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, hoe)
                .pattern(" SS")
                .pattern(" I ")
                .pattern(" I ")
                .define('S', ingot)
                .define('I', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  KermitTech.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
