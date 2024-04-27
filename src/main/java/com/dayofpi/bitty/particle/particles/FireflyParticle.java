package com.dayofpi.bitty.particle.particles;

import com.dayofpi.bitty.particle.BittyParticles;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FireflyParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    protected FireflyParticle(ClientWorld clientWorld, SpriteProvider spriteProvider, double x, double y, double z) {
        super(clientWorld, x, y - 0.125, z);
        this.setBoundingBoxSpacing(0.01f, 0.01f);
        this.velocityX = (Math.random() * 2.0 - 1.0) * (double)0.1f;
        this.velocityY = (Math.random() * 2.0 - 1.0) * (double)0.1f;
        this.velocityZ = (Math.random() * 2.0 - 1.0) * (double)0.1f;
        this.spriteProvider = spriteProvider;
        this.scale(1.0F);
        this.velocityMultiplier = 1.0f;
        this.maxAge = MathHelper.nextBetween(clientWorld.random, 100, 400);
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    protected int getBrightness(float tint) {
        return 240;
    }

    @Override
    public Optional<ParticleGroup> getGroup() {
        return Optional.of(BittyParticles.FIREFLY_GROUP);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new FireflyParticle(world, this.spriteProvider, x, y, z);
        }
    }
}
