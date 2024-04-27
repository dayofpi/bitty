package com.dayofpi.bitty.particle;

import com.dayofpi.bitty.Bitty;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleGroup;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BittyParticles {
    public static final ParticleGroup FIREFLY_GROUP = new ParticleGroup(500);
    public static final SimpleParticleType FIREFLY = particleType("firefly", true);

    private static SimpleParticleType particleType(String name, boolean alwaysSpawn) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Bitty.MOD_ID, name), FabricParticleTypes.simple(alwaysSpawn));
    }

    public static void init() {}
}
