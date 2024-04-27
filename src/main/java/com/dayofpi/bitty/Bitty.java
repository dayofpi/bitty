package com.dayofpi.bitty;

import com.dayofpi.bitty.block.BittyBlocks;
import com.dayofpi.bitty.data.BittyLootTableEvents;
import com.dayofpi.bitty.data.BittyResourceConditions;
import com.dayofpi.bitty.item.BittyItems;
import com.dayofpi.bitty.particle.BittyParticles;
import com.dayofpi.bitty.world.BittyBiomeModifications;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bitty implements ModInitializer {
	public static final String MOD_ID = "bitty";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RegistryKey<CatVariant> SPOTTED_CAT_VARIANT = RegistryKey.of(RegistryKeys.CAT_VARIANT, new Identifier("spotted"));

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing!");
		MidnightConfig.init(MOD_ID, BittyConfig.class);
		BittyResourceConditions.init();

		Registry.register(Registries.CAT_VARIANT, SPOTTED_CAT_VARIANT, new CatVariant(new Identifier("textures/entity/cat/ocelot.png")));

		BittyItems.init();
		BittyBlocks.init();
		BittyParticles.init();
		BittyBiomeModifications.init();
		BittyLootTableEvents.init();
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 1, factories -> {
			if (BittyConfig.leatherRework) {
				factories.removeIf(factory -> factory instanceof TradeOffers.BuyItemFactory && ((TradeOffers.BuyItemFactory) factory).stack.itemStack().isOf(Items.LEATHER));
				factories.add((entity, random) -> new TradeOffer(new TradedItem(BittyItems.HIDE, 6), new ItemStack(Items.EMERALD), 16, 2, 0.05f));
			}
		});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> {
			if (BittyConfig.leatherRework) {
				factories.removeIf(factory -> factory instanceof TradeOffers.BuyItemFactory && ((TradeOffers.BuyItemFactory) factory).stack.itemStack().isOf(Items.RABBIT_HIDE));
				factories.add((entity, random) -> new TradeOffer(new TradedItem(BittyItems.PELT, 9), new ItemStack(Items.EMERALD), 12, 20, 0.05f));
			}
		});
	}
}