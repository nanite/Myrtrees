package io.alwa.mods.myrtrees.common;

import io.alwa.mods.myrtrees.common.block.MyrtreesBlocks;
import io.alwa.mods.myrtrees.common.blockentity.MyrtreesBlockEntities;
import io.alwa.mods.myrtrees.common.blockentity.render.WoodenBucketRenderer;
import me.shedaniel.architectury.registry.BlockEntityRenderers;
import me.shedaniel.architectury.registry.RenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class MyrtreesClient {

    public static void init() {
        RenderTypes.register(RenderType.cutout(), MyrtreesBlocks.RUBBER_SAPLING.get());
        BlockEntityRenderers.registerRenderer(MyrtreesBlockEntities.WOODEN_BUCKET.get(), WoodenBucketRenderer::new);
    }

    private static void preAtlasStitch(TextureAtlas textureAtlas, Consumer<ResourceLocation> resourceLocationConsumer) {
        if (textureAtlas.location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            resourceLocationConsumer.accept(WoodenBucketRenderer.LATEX_TEXTURE);
        }
    }
}
