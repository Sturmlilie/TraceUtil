package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.render.BufferRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BufferRenderer.class)
public class MixinBufferRenderer {
	@Inject(at = @At("HEAD"), method = "draw(Ljava/nio/ByteBuffer;ILnet/minecraft/client/render/VertexFormat;I)V")
	private static void drawBegin(CallbackInfo info) {
		MCAnnotations.onBegin("BufferRenderer.draw");
	}

	@Inject(at = @At("RETURN"), method = "draw(Ljava/nio/ByteBuffer;ILnet/minecraft/client/render/VertexFormat;I)V")
	private static void drawEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
