package net.ancurio.gldebug.trace.mc;

import net.ancurio.gldebug.trace.Annotater;
import net.ancurio.gldebug.trace.DummyAnnotater;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.profiler.Profiler;

public class Annotations {
	private static Annotations INSTANCE = null;
	private static String NAMESPACE = "minecraft";
	
	public static void init() {
		INSTANCE = new Annotations();
	}
	
	private static boolean isInitialized() {
		return INSTANCE != null;
	}
	
	/* Generic scope annotation spanning an entire method */
	private Annotater methodTater = new DummyAnnotater();
	/* Scope annotation over one RenderLayer rendering */
	private Annotater layerTater = new DummyAnnotater();
	/* Scope annotation piggybacking Minecraft's native profiler */
	private Annotater profilerTater = new DummyAnnotater();
	public static Profiler clientProfiler = null;
	
	private Annotations() {
		methodTater = Annotater.chooseImpl(NAMESPACE, Annotater.ThreadAwareness.IGNORANT);
		layerTater = Annotater.chooseImpl(NAMESPACE, Annotater.ThreadAwareness.IGNORANT);
//		profilerTater = Annotater.chooseImpl(NAMESPACE, Annotater.ThreadAwareness.AWARE);
		
		layerTater.appendPrefix("layer");
		profilerTater.appendPrefix("profiler");
	}
	
	
	private static void onBegin(String scope) {
		if (isInitialized()) {
			INSTANCE.methodTater.push(scope);
		}
	}
	
	public static void onGenericEnd() {
		if (isInitialized()) {
			INSTANCE.methodTater.pop();
		}
	}
	
	public static void onTextureUtilPrepareImageBegin() {
		onBegin("TextureUtil.prepareImage");
	}
	
	public static void onNativeImageUploadBegin() {
		onBegin("NativeImage.upload");
	}
	
	public static void onModelLoaderUploadBegin() {
		onBegin("ModelLoader.upload");
	}
	
	public static void onBufferRendererDrawBegin() {
		onBegin("BufferRenderer.draw");
	}
	
	public static void onDrawableHelperFillBegin() {
		onBegin("DrawableHelper.fill");
	}
	
	public static void onLevelLoadingScreenRenderBegin() {
		onBegin("LevelLoadingScreen.render");
	}
	
	public static void onGameRendererRenderBegin() {
		onBegin("GameRenderer.render");
	}
	
	public static void onGameRendererRenderWorldBegin() {
		onBegin("GameRenderer.renderWorld");
	}
	
	public static void onWorldRendererRenderBegin() {
		onBegin("WorldRenderer.render");
	}
	
	public static void onWorldRendererReloadBegin() {
		onBegin("WorldRenderer.reload");
	}
	
	public static void onSpriteAtlasTextureTickAnimatedSpritesBegin() {
		onBegin("SpriteAtlasTexture.tickAnimatedSprites");
	}
	
	public static void onTextureManagerTickBegin() {
		onBegin("TextureManager.tick");
	}
   
	public static void onDiffuseLightingEnableBegin() {
		onBegin("DiffuseLighting.enable");
	}

	public static void onDiffuseLightingDisableBegin() {
		onBegin("DiffuseLighting.disable");
	}

	public static void onDiffuseLightingEnableForLevelBegin() {
		onBegin("DiffuseLighting.enableForLevel");
	}

	public static void onDiffuseLightingEnableGuiDepthLightingBegin() {
		onBegin("DiffuseLighting.enableGuiDepthLighting");
	}

	public static void onDiffuseLightingDisableGuiDepthLightingBegin() {
		onBegin("DiffuseLighting.disableGuiDepthLighting");
	}
	
	public static void onScreenRenderBegin() {
		onBegin("Screen.render");
	}
	
	public static void onItemRendererRenderItemBegin() {
		onBegin("ItemRenderer.renderItem");
	}
	
	public static void onItemRendererRenderGuiItemModelBegin() {
		onBegin("ItemRenderer.renderGuiItemModel");
	}
	
	
	public static void onRenderLayerDrawBegin(final RenderLayer layer) {
		if (isInitialized()) {
			INSTANCE.layerTater.push(layer.toString());
		}
	}
	
	public static void onRenderLayerDrawEnd() {
		INSTANCE.layerTater.pop();
	}
	
	public static void onProfilerPush(String location) {
		if (isInitialized()) {
			INSTANCE.profilerTater.push(location);
		}
	}
	
	public static void onProfilerPop() {
		if (isInitialized()) {
			INSTANCE.profilerTater.pop();
		}
	}
	
	public static void onProfilerSwap(String location) {
		if (isInitialized()) {
			INSTANCE.profilerTater.swap(location);
		}
	}
}
