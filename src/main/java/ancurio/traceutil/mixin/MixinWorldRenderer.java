package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
	@Inject(at = @At("HEAD"), method = "renderLayer(Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/util/math/MatrixStack;DDD)V")
	private void renderLayerBegin(final RenderLayer layer, final MatrixStack matrices, double d, double e, double f, CallbackInfo info) {
		MCAnnotations.onRenderLayerDrawBegin(layer);
	}

	@Inject(at = @At("RETURN"), method = "renderLayer(Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/util/math/MatrixStack;DDD)V")
	private void renderLayerEnd(CallbackInfo info) {
		MCAnnotations.onRenderLayerDrawEnd();
	}

	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V")
	private void renderBegin(CallbackInfo info) {
		MCAnnotations.onBegin("WorldRenderer.render");
	}

	@Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;FJZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lnet/minecraft/client/render/LightmapTextureManager;Lnet/minecraft/util/math/Matrix4f;)V")
	private void renderEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "reload()V")
	private void reloadBegin(CallbackInfo info) {
		MCAnnotations.onBegin("WorldRenderer.reload");
	}

	@Inject(at = @At("RETURN"), method = "reload()V")
	private void reloadEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
