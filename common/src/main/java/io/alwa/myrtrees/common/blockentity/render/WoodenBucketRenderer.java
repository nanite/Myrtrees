package io.alwa.myrtrees.common.blockentity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import io.alwa.myrtrees.common.blockentity.WoodenBucketBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class WoodenBucketRenderer extends BlockEntityRenderer<WoodenBucketBlockEntity> {

    public static ResourceLocation LATEX_TEXTURE = new ResourceLocation("myrtrees:block/latex");
    public WoodenBucketRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
    }

    @Override
    public void render(WoodenBucketBlockEntity bucket, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        int amount = bucket.latex;

        if(amount <= 0) return;

        float o0 = 4.00F / 16F;
        float o1 = 12.00F / 16F;

        float y0 = 1.10F / 16F;
        float y11 = 10.8F / 16F;

        float fluidLevel = y0 + ((y11 - y0) * amount / (float) bucket.MAX_LATEX);

        Matrix4f m = poseStack.last().pose();
        Matrix3f n = poseStack.last().normal();


        VertexConsumer buffer = multiBufferSource.getBuffer(RenderType.translucent());

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(LATEX_TEXTURE);

        float u0 = sprite.getU0();
        float v0 = sprite.getV0();
        float u1 = sprite.getU1();
        float v1 = sprite.getV1();
        float uo = (u1 - u0) / 16F;
        float vo = (v1 - v0) / 16F;

        int color = 0xffffffff;
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        LevelRenderer renderer = (x, z, u, v) -> buffer.vertex(m, x, fluidLevel, z).color(r, g, b, a).uv(u, v).overlayCoords(j).uv2(i).normal(n, 0, 1, 0).endVertex();

        if(fluidLevel >= 0.18999999999990003 && fluidLevel <= 0.37187500000000007D){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;
        }
        if(fluidLevel >= 0.37187500000000007){
            o0 = 3.00F / 16F;
            o1 = 13.00F / 16F;

            // West
            renderer.vertex(2F/16F, 4F/16F, u0 + uo, v0 + vo);
            renderer.vertex(2F/16F, 12F/16F, u1 - uo, v0 + vo);
            renderer.vertex(3F/16F, 12F/16F, u1 - uo, v1 - vo);
            renderer.vertex(3F/16F, 4F/16F, u0 + uo, v1 - vo);

            // South
            renderer.vertex(4F/16F, 13F/16F, u0 + uo, v0 + vo);
            renderer.vertex(4F/16F, 14F/16F, u1 - uo, v0 + vo);
            renderer.vertex(12F/16F, 14F/16F, u1 - uo, v1 - vo);
            renderer.vertex(12F/16F, 13F/16F, u0 + uo, v1 - vo);

            // East
            renderer.vertex(13F/16F, 4F/16F, u0 + uo, v0 + vo);
            renderer.vertex(13F/16F, 12F/16F, u1 - uo, v0 + vo);
            renderer.vertex(14F/16F, 12F/16F, u1 - uo, v1 - vo);
            renderer.vertex(14F/16F, 4F/16F, u0 + uo, v1 - vo);

            // North
            renderer.vertex(4F/16F, 2F/16F, u0 + uo, v0 + vo);
            renderer.vertex(4F/16F, 3F/16F, u1 - uo, v0 + vo);
            renderer.vertex(12F/16F, 3F/16F, u1 - uo, v1 - vo);
            renderer.vertex(12F/16F, 2F/16F, u0 + uo, v1 - vo);
        }
        //UP
        if (fluidLevel < 1D)
        {
            renderer.vertex(o0, o0, u0 + uo, v0 + vo);
            renderer.vertex(o0, o1, u1 - uo, v0 + vo);
            renderer.vertex(o1, o1, u1 - uo, v1 - vo);
            renderer.vertex(o1, o0, u0 + uo, v1 - vo);
        }
    }

    private interface LevelRenderer
    {
        void vertex(float x, float z, float u, float v);
    }
}
