package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.render.BufferRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BufferRenderer.class)
public class MixinBufferRenderer {
	@Inject(at = @At("HEAD"), method = "draw(Ljava/nio/ByteBuffer;ILnet/minecraft/client/render/VertexFormat;I)V")
	private static void drawBegin(CallbackInfo info) {
		Annotations.onBegin("BufferRenderer.draw");
	}

	@Inject(at = @At("RETURN"), method = "draw(Ljava/nio/ByteBuffer;ILnet/minecraft/client/render/VertexFormat;I)V")
	private static void drawEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
