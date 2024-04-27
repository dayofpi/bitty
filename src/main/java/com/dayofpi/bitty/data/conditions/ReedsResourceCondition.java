package com.dayofpi.bitty.data.conditions;

import com.dayofpi.bitty.BittyConfig;
import com.dayofpi.bitty.data.BittyResourceConditions;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

public record ReedsResourceCondition() implements ResourceCondition {
    public static final MapCodec<ReedsResourceCondition> CODEC = MapCodec.unit(ReedsResourceCondition::new);

    @Override
    public ResourceConditionType<?> getType() {
        return BittyResourceConditions.REEDS_RESOURCE_CONDITION;
    }

    @Override
    public boolean test(@Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return BittyConfig.reeds;
    }
}
