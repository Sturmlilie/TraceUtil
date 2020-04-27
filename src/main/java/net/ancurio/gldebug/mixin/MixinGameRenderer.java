package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
	@Inject(at = @At("HEAD"), method = "render(FJZ)V")
	private void renderBegin(CallbackInfo info) {
		Annotations.onBegin("GameRenderer.render");
	}

	@Inject(at = @At("RETURN"), method = "render(FJZ)V")
	private void renderEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V")
	private void renderWorldBegin(CallbackInfo info) {
		Annotations.onBegin("GameRenderer.renderWorld");
	}

	@Inject(at = @At("RETURN"), method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V")
	private void renderWorldEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
