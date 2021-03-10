package io.alwa.myrtrees.common;

import io.alwa.myrtrees.common.blockentity.render.WoodenBucketRenderer;
import me.shedaniel.architectury.event.events.TextureStitchEvent;
import me.shedaniel.architectury.registry.BlockEntityRenderers;
import me.shedaniel.architectury.registry.RenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class MyrtreesClient {

    public static void init() {
        RenderTypes.register(RenderType.cutout(), Myrtrees.RUBBER_SAPLING.get());
        BlockEntityRenderers.registerRenderer(Myrtrees.WOODEN_BUCKET_ENTITY_TYPE.get(), WoodenBucketRenderer::new);
        TextureStitchEvent.PRE.register(MyrtreesClient::preAtlasStitch);
    }

    private static void preAtlasStitch(TextureAtlas textureAtlas, Consumer<ResourceLocation> resourceLocationConsumer) {
        if (textureAtlas.location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            resourceLocationConsumer.accept(WoodenBucketRenderer.LATEX_TEXTURE);
        }
    }
}
