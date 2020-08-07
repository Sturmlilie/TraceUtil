package ancurio.traceutil.anno;

import ancurio.traceutil.Main;
import ancurio.traceutil.anno.Annotater;
import ancurio.traceutil.anno.DummyAnnotater;
import ancurio.traceutil.anno.backend.BackendLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.lwjgl.opengl.GL11;

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

	public static void onTextureRegistered(final Identifier id, final AbstractTexture texture) {
		// Apparently the texture needs to be bound first before it can be labeled?
		int glId = texture.getGlId();
		int previousId = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, glId);

		BackendLoader.I.objectLabel(glId, Annotater.ObjectType.TEXTURE, id.toString());

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, previousId);
	}
}
