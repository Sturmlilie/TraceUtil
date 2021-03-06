package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VertexConsumerProvider.Immediate.class)
public class MixinVertexConsumerProviderImmediate {
	@Inject(at = @At("HEAD"), method = "draw(Lnet/minecraft/client/render/RenderLayer;)V")
	private void drawBegin(final RenderLayer layer, CallbackInfo info) {
		MCAnnotations.onRenderLayerDrawBegin(layer);
	}
	
	@Inject(at = @At("RETURN"), method = "draw(Lnet/minecraft/client/render/RenderLayer;)V")
	private void drawEnd(CallbackInfo info) {
		MCAnnotations.onRenderLayerDrawEnd();
	}
}
