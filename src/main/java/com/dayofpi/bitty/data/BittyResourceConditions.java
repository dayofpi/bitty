package com.dayofpi.bitty.data;

import com.dayofpi.bitty.Bitty;
import com.dayofpi.bitty.data.conditions.*;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;

public class BittyResourceConditions {
    public static final ResourceConditionType<ApplePiesResourceCondition> APPLE_PIES_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "apple_pies"), ApplePiesResourceCondition.CODEC);
    public static final ResourceConditionType<CalamariResourceCondition> CALAMARI_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "calamari"), CalamariResourceCondition.CODEC);
    public static final ResourceConditionType<ReedsResourceCondition> REEDS_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "reeds"), ReedsResourceCondition.CODEC);
    public static final ResourceConditionType<RoastedCarrotsResourceCondition> ROASTED_CARROTS_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "roasted_carrots"), RoastedCarrotsResourceCondition.CODEC);
    public static final ResourceConditionType<LeatherReworkResourceCondition> LEATHER_REWORK_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "leather_rework"), LeatherReworkResourceCondition.CODEC);
    public static final ResourceConditionType<CraftableSaddleResourceCondition> CRAFTABLE_SADDLE_RESOURCE_CONDITION = ResourceConditionType.create(new Identifier(Bitty.MOD_ID, "craftable_saddle"), CraftableSaddleResourceCondition.CODEC);

    public static void init() {
        ResourceConditions.register(APPLE_PIES_RESOURCE_CONDITION);
        ResourceConditions.register(CALAMARI_RESOURCE_CONDITION);
        ResourceConditions.register(REEDS_RESOURCE_CONDITION);
        ResourceConditions.register(ROASTED_CARROTS_RESOURCE_CONDITION);
        ResourceConditions.register(LEATHER_REWORK_RESOURCE_CONDITION);
        ResourceConditions.register(CRAFTABLE_SADDLE_RESOURCE_CONDITION);
    }
}
