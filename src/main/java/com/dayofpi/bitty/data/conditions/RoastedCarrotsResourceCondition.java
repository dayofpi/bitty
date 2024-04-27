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

public record RoastedCarrotsResourceCondition(boolean enabled) implements ResourceCondition {
    public static final MapCodec<RoastedCarrotsResourceCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("enabled").forGetter(RoastedCarrotsResourceCondition::enabled)
    ).apply(instance, RoastedCarrotsResourceCondition::new));

    @Override
    public ResourceConditionType<?> getType() {
        return BittyResourceConditions.ROASTED_CARROTS_RESOURCE_CONDITION;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return enabled == BittyConfig.roastedCarrots;
    }
}
