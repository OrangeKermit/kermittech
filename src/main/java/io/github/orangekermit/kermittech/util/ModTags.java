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
        public static final TagKey<Block> NEDS_ALUMINIUM_TOOL = tag("needs_aluminium_tool");

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

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class ForgeItems {
        public static final TagKey<Item> INGOT = tag("ingots");
        public static final TagKey<Item> NUGGET = tag("nuggets");

        public static final TagKey<Item> ALUMINIUM_INGOT = tag("ingots/aluminium");
        public static final TagKey<Item> ALUMINIUM_NUGGET = tag("nuggets/aluminium");
        public static final TagKey<Item> RAW_ALUMINIUM = tag("raw_materials/aluminium");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
