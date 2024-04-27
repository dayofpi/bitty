package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.BittyConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RabbitEntity.class)
public abstract class RabbitEntityMixin extends AnimalEntity {
    protected RabbitEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        int computeFallDamage = super.computeFallDamage(fallDistance, damageMultiplier);
        if (BittyConfig.rabbitFallDamageReduction)
            return computeFallDamage - 10;
        else return computeFallDamage;
    }
}
