package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.world.BittyConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SaplingGenerator.class)
public class SaplingGeneratorMixin {
    @Shadow @Final private String id;

    @Inject(method = "getSmallTreeFeature", at = @At("HEAD"), cancellable = true)
    private void addSmallDarkOaks(Random random, boolean flowersNearby, CallbackInfoReturnable<RegistryKey<ConfiguredFeature<?, ?>>> cir) {
        if (this.id.equals("dark_oak") && BittyConfig.smallDarkOaks) {
            cir.setReturnValue(BittyConfiguredFeatures.SMALL_DARK_OAK);
        }
    }
}
