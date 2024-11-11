package io.github.orangekermit.kermittech.datagen;

import io.github.orangekermit.kermittech.KermitTech;
import io.github.orangekermit.kermittech.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KermitTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RAW_ALUMINIUM);
        simpleItem(ModItems.ALUMINIUM_INGOT);
        simpleItem(ModItems.ALUMINIUM_NUGGET);
        simpleItem(ModItems.ALUMINIUM_COIL);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KermitTech.MOD_ID,"item/" + item.getId().getPath()));
    }
}