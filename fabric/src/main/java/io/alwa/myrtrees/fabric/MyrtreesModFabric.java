package io.alwa.myrtrees.fabric;

import io.alwa.myrtrees.common.Myrtrees;
import net.fabricmc.api.ModInitializer;

public class MyrtreesModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Myrtrees.init();
        Myrtrees.afterRegistries();
        Myrtrees.clientInit();
    }
}
