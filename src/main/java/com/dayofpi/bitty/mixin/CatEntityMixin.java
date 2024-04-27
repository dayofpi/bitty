package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.BittyConfig;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends TameableEntity {
    @Shadow public abstract void setVariant(RegistryEntry<CatVariant> registryEntry);

    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private void spawnJungleCats(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        if (BittyConfig.ocelotAsCatVariant && !world.toServerWorld().isNearOccupiedPointOfInterest(this.getBlockPos(), 2) && world.getBiome(this.getBlockPos()).isIn(BiomeTags.IS_JUNGLE)) {
            entityData = super.initialize(world, difficulty, spawnReason, entityData);
            this.setVariant(Registries.CAT_VARIANT.entryOf(Bitty.SPOTTED_CAT_VARIANT));
            this.setPersistent();
            cir.setReturnValue(entityData);
        }
    }
}
