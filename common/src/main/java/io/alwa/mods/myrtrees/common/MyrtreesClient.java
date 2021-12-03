package io.alwa.mods.myrtrees.common;

import dev.architectury.event.events.client.ClientTextureStitchEvent;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import io.alwa.mods.myrtrees.common.blockentity.MyrtreesBlockEntities;
import io.alwa.mods.myrtrees.common.blockentity.render.WoodenBucketRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Consumer;

public class MyrtreesClient {
    public static void init() {
        RenderTypeRegistry.register(RenderType.cutout(), MyrtreesBlocks.RUBBERWOOD_SAPLING.get());
        // TODO: What??
        BlockEntityRendererRegistry.register(MyrtreesBlockEntities.WOODEN_BUCKET.get(), WoodenBucketRenderer::new);
        ClientTextureStitchEvent.PRE.register(MyrtreesClient::preAtlasStitch);
    }

    private static void preAtlasStitch(TextureAtlas textureAtlas, Consumer<ResourceLocation> resourceLocationConsumer) {
        if (textureAtlas.location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            resourceLocationConsumer.accept(WoodenBucketRenderer.LATEX_TEXTURE);
        }
    }
}
