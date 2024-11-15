package io.github.orangekermit.kermittech.item;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier ALUMINIUM = TierSortingRegistry.registerTier(
            new ForgeTier(2, 180, 5f, 4f, 12,
                    ModTags.Blocks.NEEDS_ALUMINIUM_TOOL, () -> Ingredient.of(ModItems.ALUMINIUM_INGOT.get())),
            new ResourceLocation(KermitTech.MOD_ID, "aluminium"), List.of(Tiers.STONE), List.of());

    public static final Tier TIN = TierSortingRegistry.registerTier(
            new ForgeTier(2, 180, 5f, 4f, 12,
                    ModTags.Blocks.NEEDS_TIN_TOOL, () -> Ingredient.of(ModItems.TIN_INGOT.get())),
            new ResourceLocation(KermitTech.MOD_ID, "tin"), List.of(Tiers.STONE), List.of());

}
