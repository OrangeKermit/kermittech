package io.github.orangekermit.kermittech.compat;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import org.jetbrains.annotations.Nullable;

public class SmeltingCategory implements IRecipeCategory<SmeltingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(KermitTech.MOD_ID, "smelting");
    public static final ResourceLocation TEXTURE = new ResourceLocation(KermitTech.MOD_ID,
            "textures/gui/electrical_furnace_gui.png");

    public static final RecipeType<SmeltingRecipe> SMELTING_TYPE =
            new RecipeType<>(UID, SmeltingRecipe.class);

    private final IDrawable backgroud;
    private final IDrawable icon;

    public SmeltingCategory(IGuiHelper helper) {
        this.backgroud = helper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ELECTRICAL_FURNACE.get()));
    }

    @Override
    public RecipeType<SmeltingRecipe> getRecipeType() {
        return SMELTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.jei.category.smelting");
    }

    @Override
    public IDrawable getBackground() {
        return this.backgroud;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SmeltingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 35).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT,114, 35).addItemStack(recipe.getResultItem(null));
    }
}
