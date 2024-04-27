package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.item.BittyItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoxEntity.class)
public abstract class FoxEntityMixin extends AnimalEntity {
    protected FoxEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initEquipment", at = @At("HEAD"), cancellable = true)
    private void swapRabbitHideForPelt(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        if (BittyConfig.leatherRework) {
            if (random.nextFloat() < 0.2f) {
                float f = random.nextFloat();
                ItemStack itemStack = f < 0.05f ? new ItemStack(Items.EMERALD) : (f < 0.2f ? new ItemStack(Items.EGG) : (f < 0.4f ? (random.nextBoolean() ? new ItemStack(Items.RABBIT_FOOT) : new ItemStack(BittyItems.PELT)) : (f < 0.6f ? new ItemStack(Items.WHEAT) : (f < 0.8f ? new ItemStack(Items.LEATHER) : new ItemStack(Items.FEATHER)))));
                this.equipStack(EquipmentSlot.MAINHAND, itemStack);
            }
            ci.cancel();
        }
    }
}
