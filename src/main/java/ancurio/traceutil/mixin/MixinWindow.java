package ancurio.traceutil.mixin;

import ancurio.traceutil.Main;
import ancurio.traceutil.anno.MCAnnotations;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import net.minecraft.client.util.Window;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public class MixinWindow {
	private final static Map<Integer, String> errorCodes;
	static {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0x10001, "GLFW_NOT_INITIALIZED: GLFW has not been initialized.");
		map.put(0x10002, "GLFW_NO_CURRENT_CONTEXT: No context is current for this thread.");
		map.put(0x10003, "GLFW_INVALID_ENUM: One of the arguments to the function was an invalid enum value.");
		map.put(0x10004, "GLFW_INVALID_VALUE: One of the arguments to the function was an invalid value.");
		map.put(0x10005, "GLFW_OUT_OF_MEMORY: A memory allocation failed.");
		map.put(0x10006, "GLFW_API_UNAVAILABLE: GLFW could not find support for the requested API on the system.");
		map.put(0x10007, "GLFW_VERSION_UNAVAILABLE: The requested OpenGL or OpenGL ES version is not available.");
		map.put(0x10008, "GLFW_PLATFORM_ERROR: A platform-specific error occurred that does not match any of the more specific categories.");
		map.put(0x10009, "GLFW_FORMAT_UNAVAILABLE: The requested format is not supported or available.");
		map.put(0x1000A, "GLFW_NO_WINDOW_CONTEXT: The specified window does not have an OpenGL context.");
		errorCodes = Collections.unmodifiableMap(map);
    }

	/**
	 * @author Ancurio
	 * @reason Mojang's code falsely prints "GL ERROR", which is very misleading
	 *         as this is actually a GLFW-internal error.
	 */
	@Overwrite
	public void logGlError(int error, long description) {
		final String errorType = errorCodes.getOrDefault(error, "Unknown GLFW error");
		final String descString = MemoryUtil.memUTF8(description);

		Main.log("===== GLFW error detected =====");
		Main.log(errorType);
		Main.log("Details: " + descString);
	}
}
