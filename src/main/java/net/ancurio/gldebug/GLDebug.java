package net.ancurio.gldebug;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GLDebug implements ModInitializer {
	public static final String MODID = "gldebug";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static void log(String str) {
		LOGGER.info("[{}] {}", MODID, str);
	}

	@Override
	public void onInitialize() {
		Annotations.init();
		log("Initialized");
	}
}
