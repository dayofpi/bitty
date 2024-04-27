package com.dayofpi.bitty.data.conditions;

import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.data.BittyResourceConditions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

public record ApplePiesResourceCondition(boolean enabled) implements ResourceCondition {
    public static final MapCodec<ApplePiesResourceCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("enabled").forGetter(ApplePiesResourceCondition::enabled)
    ).apply(instance, ApplePiesResourceCondition::new));

    @Override
    public ResourceConditionType<?> getType() {
        return BittyResourceConditions.APPLE_PIES_RESOURCE_CONDITION;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return enabled == BittyConfig.applePies;
    }
}
