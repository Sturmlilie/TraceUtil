package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
	@Inject(at = @At("HEAD"), method = "render(FJZ)V")
	private void renderBegin(CallbackInfo info) {
		MCAnnotations.onBegin("GameRenderer.render");
	}

	@Inject(at = @At("RETURN"), method = "render(FJZ)V")
	private void renderEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V")
	private void renderWorldBegin(CallbackInfo info) {
		MCAnnotations.onBegin("GameRenderer.renderWorld");
	}

	@Inject(at = @At("RETURN"), method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V")
	private void renderWorldEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
