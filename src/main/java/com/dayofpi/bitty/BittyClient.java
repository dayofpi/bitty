package com.dayofpi.bitty;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.particle.BittyParticles;
import com.dayofpi.bitty.particle.particles.FireflyParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class BittyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(BittyParticles.FIREFLY, FireflyParticle.Factory::new);
        ModelPredicateProviderRegistry.register(Items.LEATHER, new Identifier("leather_rework"), (stack, world, entity, seed) -> BittyConfig.leatherRework ? 1.0F : 0.0F);
        BlockRenderLayerMap.INSTANCE.putBlock(BittyBlocks.REEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BittyBlocks.LUMINOUS_REEDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BittyBlocks.REED_CROP, RenderLayer.getCutout());
    }
}
