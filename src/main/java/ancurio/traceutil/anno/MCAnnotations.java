package ancurio.traceutil.anno;

import ancurio.traceutil.Main;
import ancurio.traceutil.anno.Annotater;
import ancurio.traceutil.anno.DummyAnnotater;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.profiler.Profiler;

public class MCAnnotations {
	private static MCAnnotations INSTANCE;
	private final static String NAMESPACE = "minecraft";

	public static Profiler clientProfiler;

	/* Generic scope annotation spanning an entire method */
	private Annotater methodTater = new Annotater(NAMESPACE);
	/* Scope annotation over one RenderLayer rendering */
	private Annotater layerTater = new Annotater(NAMESPACE);
	/* Scope annotation piggybacking Minecraft's native profiler */
	private Annotater profilerTater = new DummyAnnotater();

	public static void init() {
		INSTANCE = new MCAnnotations();

//		INSTANCE.profilerTater = new Annotater(NAMESPACE);

		INSTANCE.layerTater.appendPrefix("layer");
		INSTANCE.profilerTater.appendPrefix("profiler");
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
