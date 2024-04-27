package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.BittyConfig;
import net.minecraft.client.color.world.FoliageColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoliageColors.class)
public class FoliageColorsMixin {
    @Inject(method = "getBirchColor", at = @At("HEAD"), cancellable = true)
    private static void changeBirchColor(CallbackInfoReturnable<Integer> cir) {
        if (BittyConfig.birchOverhaul) {
            cir.setReturnValue(15136071);
        }
    }
}
