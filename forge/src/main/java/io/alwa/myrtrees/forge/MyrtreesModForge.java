package io.alwa.myrtrees.forge;

import io.alwa.myrtrees.common.Myrtrees;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Myrtrees.MOD_ID)
public class MyrtreesModForge {
    public MyrtreesModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Myrtrees.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Myrtrees.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        Myrtrees.afterRegistries();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        Myrtrees.clientInit();
    }
}
