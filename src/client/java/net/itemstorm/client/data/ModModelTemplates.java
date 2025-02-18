package net.itemstorm.client.data;

import net.itemstorm.ItemStorm;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModModelTemplates {

    private static ModelTemplate create(TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.empty(), Optional.empty(), textureSlots);
    }

    private static ModelTemplate create(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, "block/" + string)), Optional.empty(), textureSlots);
    }

    private static ModelTemplate createItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, "item/" + string)), Optional.empty(), textureSlots);
    }

    private static ModelTemplate create(String string, String string2, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, "block/" + string)), Optional.of(string2), textureSlots);
    }
}
