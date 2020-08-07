package ancurio.traceutil;

import ancurio.traceutil.anno.MCAnnotations;
import ancurio.traceutil.anno.backend.BackendLoader;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ClientModInitializer {
	public static final String MODID = "traceutil";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static void log(String str) {
		LOGGER.info("[{}] {}", MODID, str);
	}

	@Override
	public void onInitializeClient() {
		MCAnnotations.init();
		log("Initialized");
	}

	public static void onGlContextValid() {
		BackendLoader.loadGlBackend();
	}
}
