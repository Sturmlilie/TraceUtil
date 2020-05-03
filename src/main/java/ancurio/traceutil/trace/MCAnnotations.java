package ancurio.traceutil.trace;

import ancurio.traceutil.Main;
import ancurio.traceutil.trace.Annotater;
import ancurio.traceutil.trace.DummyAnnotater;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.profiler.Profiler;

public class MCAnnotations {
	private static MCAnnotations INSTANCE;
	private final static String NAMESPACE = "minecraft";

	public static Profiler clientProfiler;

	/* Generic scope annotation spanning an entire method */
	private Annotater methodTater = new DummyAnnotater();
	/* Scope annotation over one RenderLayer rendering */
	private Annotater layerTater = new DummyAnnotater();
	/* Scope annotation piggybacking Minecraft's native profiler */
	private Annotater profilerTater = new DummyAnnotater();

	public static void init() {
		INSTANCE = new MCAnnotations();
	}

	public static void initAfterGLContextValid() {
		INSTANCE.methodTater = Annotater.chooseImpl(NAMESPACE);
		INSTANCE.layerTater = Annotater.chooseImpl(NAMESPACE);
//		INSTANCE.profilerTater = Annotater.chooseImpl(NAMESPACE, Annotater.ThreadAwareness.AWARE);

		INSTANCE.layerTater.appendPrefix("layer");
		INSTANCE.profilerTater.appendPrefix("profiler");

		String ext = Annotater.supportedImplExt();

		if (!ext.isEmpty()) {
			Main.log(ext + " found, starting annotations");
		} else {
			Main.log("No debug extensions found");
		}
	}

	public static void onBegin(String scope) {
		INSTANCE.methodTater.push(scope);
	}

	public static void onGenericEnd() {
		INSTANCE.methodTater.pop();
	}

	public static void onRenderLayerDrawBegin(final RenderLayer layer) {
		INSTANCE.layerTater.push(layer.toString());
	}

	public static void onRenderLayerDrawEnd() {
		INSTANCE.layerTater.pop();
	}

	public static void onProfilerPush(String location) {
		INSTANCE.profilerTater.push(location);
	}

	public static void onProfilerPop() {
		INSTANCE.profilerTater.pop();
	}

	public static void onProfilerSwap(String location) {
		INSTANCE.profilerTater.swap(location);
	}
}
