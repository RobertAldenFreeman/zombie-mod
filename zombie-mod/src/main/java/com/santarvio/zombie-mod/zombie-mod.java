package com.${username}.${project_name};

import com.${username}.${project_name}.config.Config;
import com.${username}.${project_name}.events.Events;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ZombieMod.MOD_ID)
public class ZombieMod {
    public static final String MOD_ID = "${project_name}";
    public static final Logger LOGGER = LogManager.getLogger();

    public ZombieMod() {
        LOGGER.info("Initializing Zombie Mod");
        
        // Get the mod event bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register the setup method for mod loading
        modEventBus.addListener(this::setup);
        
        // Register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        
        // Register our event handlers
        Events.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Zombie Mod Setup: Prepare for faster, bigger hordes!");
    }
}
