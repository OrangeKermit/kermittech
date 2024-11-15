package io.github.orangekermit.kermittech.item;

import io.github.orangekermit.kermittech.KermitTech;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KermitTech.MOD_ID);

    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_NUGGET = ITEMS.register("aluminium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALUMINIUM_SWORD = ITEMS.register("aluminium_sword",
            () -> new SwordItem(ModToolTiers.ALUMINIUM, 0, -2.4f,new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_PICKAXE = ITEMS.register("aluminium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALUMINIUM, -2, -2.8f,new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_AXE = ITEMS.register("aluminium_axe",
            () -> new AxeItem(ModToolTiers.ALUMINIUM, 4, -3.2f,new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_SHOVEL = ITEMS.register("aluminium_shovel",
            () -> new ShovelItem(ModToolTiers.ALUMINIUM, -1.5f, -3.0f,new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_HOE = ITEMS.register("aluminium_hoe",
            () -> new HoeItem(ModToolTiers.ALUMINIUM, -4, -2.0f,new Item.Properties()));

    public static final RegistryObject<Item> ALUMINIUM_HELMET = ITEMS.register("aluminium_helmet",
            () -> new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_CHESTPLATE = ITEMS.register("aluminium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_LEGGINGS = ITEMS.register("aluminium_leggings",
            () -> new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ALUMINIUM_BOOTS = ITEMS.register("aluminium_boots",
            () -> new ArmorItem(ModArmorMaterials.ALUMINIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword",
            () -> new SwordItem(ModToolTiers.TIN, 0, -2.4f,new Item.Properties()));
    public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TIN, -2, -2.8f,new Item.Properties()));
    public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe",
            () -> new AxeItem(ModToolTiers.TIN, 4, -3.2f,new Item.Properties()));
    public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel",
            () -> new ShovelItem(ModToolTiers.TIN, -1.5f, -3.0f,new Item.Properties()));
    public static final RegistryObject<Item> TIN_HOE = ITEMS.register("tin_hoe",
            () -> new HoeItem(ModToolTiers.TIN, -4, -2.0f,new Item.Properties()));

    public static final RegistryObject<Item> TIN_HELMET = ITEMS.register("tin_helmet",
            () -> new ArmorItem(ModArmorMaterials.TIN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> TIN_CHESTPLATE = ITEMS.register("tin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TIN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> TIN_LEGGINGS = ITEMS.register("tin_leggings",
            () -> new ArmorItem(ModArmorMaterials.TIN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> TIN_BOOTS = ITEMS.register("tin_boots",
            () -> new ArmorItem(ModArmorMaterials.TIN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ALUMINIUM_COIL = ITEMS.register("aluminium_coil",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_COIL = ITEMS.register("tin_coil",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
