package com.marisa.ume;

import com.marisa.ume.block.BlockRegistry;
import com.marisa.ume.item.ItemRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Ume.MODID)
public class Ume {

    public static final String MODID = "ume";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Ume() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        public static void onClientSetup1() {
            List<OreConfiguration.TargetBlockState> ORE_DIAMOND_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_ORE.defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()));
            Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DIAMOND_SMALL = FeatureUtils.register("ore_diamond_small", Feature.ORE, new OreConfiguration(ORE_DIAMOND_TARGET_LIST, 4, 0.5F));
            Holder<PlacedFeature> diamondUp = PlacementUtils.register("ore_diamond_up", ORE_DIAMOND_SMALL, commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(32), VerticalAnchor.aboveBottom(64))));
            Holder<PlacedFeature> diamondDown = PlacementUtils.register("ore_diamond_down", ORE_DIAMOND_SMALL, commonOrePlacement(32, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-48), VerticalAnchor.aboveBottom(32))));
            
        }

        private static List<PlacementModifier> commonOrePlacement(int times, PlacementModifier clamp) {
            return List.of(CountPlacement.of(times), InSquarePlacement.spread(), clamp, BiomeFilter.biome());
        }
        
    }
}
