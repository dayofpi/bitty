package com.dayofpi.bitty.mixin;

import com.dayofpi.bitty.BittyConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin extends Item {
    public TridentItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        if (BittyConfig.repairableTridents) {
            return ingredient.isOf(Items.COPPER_INGOT);
        }
        return super.canRepair(stack, ingredient);
    }
}
