package me.wawwior.levelless;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(Levelless.MODID)
public class Levelless {
    public static final String MODID = "levelless";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Levelless(IEventBus modEventBus, ModContainer modContainer) {
    }
}
