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
                        pOutput.accept(ModItems.RAW_ALUMINIUM.get());
                        pOutput.accept(ModItems.ALUMINIUM_INGOT.get());
                        pOutput.accept(ModItems.ALUMINIUM_NUGGET.get());

                        pOutput.accept(ModBlocks.ALUMINIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());

                        pOutput.accept(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.ALUMINIUM_BLOCK.get());

                        pOutput.accept(ModItems.ALUMINIUM_COIL.get());

                        pOutput.accept(ModBlocks.COAL_GENERATOR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
