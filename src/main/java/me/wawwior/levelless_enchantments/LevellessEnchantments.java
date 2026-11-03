package me.wawwior.levelless_enchantments;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(LevellessEnchantments.MODID)
public class LevellessEnchantments {
    public static final String MODID = "levelless_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LevellessEnchantments(IEventBus modEventBus, ModContainer modContainer) {
    }
}
