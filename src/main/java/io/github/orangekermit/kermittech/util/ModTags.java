package io.github.orangekermit.kermittech.util;

import io.github.orangekermit.kermittech.KermitTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ALUMINIUM_TOOL = tag("needs_aluminium_tool");
        public static final TagKey<Block> NEEDS_TIN_TOOL = tag("needs_tin_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(KermitTech.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(KermitTech.MOD_ID, name));
        }
    }

    public static class ForgeBlocks {
        public static final TagKey<Block> ORE = tag("ores");
        public static final TagKey<Block> STORAGE_BLOCK = tag("storage_blocks");

        public static final TagKey<Block> ALUMINIUM_ORE = tag("ores/aluminium");
        public static final TagKey<Block> ALUMINIUM_BLOCK = tag("storage_blocks/aluminium");
        public static final TagKey<Block> RAW_ALUMINIUM_BLOCK = tag("storage_blocks/raw_aluminium");
        public static final TagKey<Block> TIN_ORE = tag("ores/tin");
        public static final TagKey<Block> TIN_BLOCK = tag("storage_blocks/tin");
        public static final TagKey<Block> RAW_TIN_BLOCK = tag("storage_blocks/raw_tin");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class ForgeItems {
        public static final TagKey<Item> INGOT = tag("ingots");
        public static final TagKey<Item> NUGGET = tag("nuggets");
        public static final TagKey<Item> RAW_MATERIALS = tag("raw_materials");
        public static final TagKey<Item> STORAGE_BLOCKS= tag("storage_blocks");
        public static final TagKey<Item> ORES = tag("ores");

        public static final TagKey<Item> ALUMINIUM_INGOT = tag("ingots/aluminium");
        public static final TagKey<Item> ALUMINIUM_NUGGET = tag("nuggets/aluminium");
        public static final TagKey<Item> RAW_ALUMINIUM = tag("raw_materials/aluminium");

        public static final TagKey<Item> TIN_INGOT = tag("ingots/tin");
        public static final TagKey<Item> TIN_NUGGET = tag("nuggets/tin");
        public static final TagKey<Item> RAW_TIN = tag("raw_materials/tin");

        public static final TagKey<Item> ALUMINIUM_ORE = tag("storage_blocks/aluminium");
        public static final TagKey<Item> ALUMINIUM_BLOCK = tag("storage_blocks/aluminium");
        public static final TagKey<Item> RAW_ALUMINIUM_BLOCK = tag("storage_blocks/raw_aluminium");
        public static final TagKey<Item> TIN_ORE = tag("ores/tin");
        public static final TagKey<Item> TIN_BLOCK = tag("storage_blocks/tin");
        public static final TagKey<Item> RAW_TIN_BLOCK = tag("storage_blocks/raw_tin");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
