package io.github.orangekermit.kermittech.item;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KermitTech.MOD_ID);

    public static final RegistryObject<CreativeModeTab> KERMITTECH_TAB = CREATIVE_MODE_TABS.register("kermittech_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ALUMINIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.kermittech_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ALUMINIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
                        pOutput.accept(ModBlocks.TIN_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_TIN_ORE.get());

                        pOutput.accept(ModBlocks.RAW_TIN_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_ALUMINIUM_BLOCK.get());

                        pOutput.accept(ModBlocks.ALUMINIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.TIN_BLOCK.get());

                        pOutput.accept(ModItems.RAW_ALUMINIUM.get());
                        pOutput.accept(ModItems.ALUMINIUM_INGOT.get());
                        pOutput.accept(ModItems.ALUMINIUM_NUGGET.get());

                        pOutput.accept(ModItems.RAW_TIN.get());
                        pOutput.accept(ModItems.TIN_INGOT.get());
                        pOutput.accept(ModItems.TIN_NUGGET.get());

                        pOutput.accept(ModItems.ALUMINIUM_COIL.get());
                        pOutput.accept(ModItems.TIN_COIL.get());

                        pOutput.accept(ModBlocks.COAL_GENERATOR.get());

                        pOutput.accept(ModItems.ALUMINIUM_HELMET.get());
                        pOutput.accept(ModItems.ALUMINIUM_CHESTPLATE.get());
                        pOutput.accept(ModItems.ALUMINIUM_LEGGINGS.get());
                        pOutput.accept(ModItems.ALUMINIUM_BOOTS.get());

                        pOutput.accept(ModItems.TIN_HELMET.get());
                        pOutput.accept(ModItems.TIN_CHESTPLATE.get());
                        pOutput.accept(ModItems.TIN_LEGGINGS.get());
                        pOutput.accept(ModItems.TIN_BOOTS.get());

                        pOutput.accept(ModItems.ALUMINIUM_SWORD.get());
                        pOutput.accept(ModItems.ALUMINIUM_PICKAXE.get());
                        pOutput.accept(ModItems.ALUMINIUM_AXE.get());
                        pOutput.accept(ModItems.ALUMINIUM_SHOVEL.get());
                        pOutput.accept(ModItems.ALUMINIUM_HOE.get());

                        pOutput.accept(ModItems.TIN_SWORD.get());
                        pOutput.accept(ModItems.TIN_PICKAXE.get());
                        pOutput.accept(ModItems.TIN_AXE.get());
                        pOutput.accept(ModItems.TIN_SHOVEL.get());
                        pOutput.accept(ModItems.TIN_HOE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
