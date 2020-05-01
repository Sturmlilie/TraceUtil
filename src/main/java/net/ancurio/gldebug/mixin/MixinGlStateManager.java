package net.ancurio.gldebug.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.ancurio.gldebug.trace.mc.Annotations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GlStateManager.class)
public class MixinGlStateManager {
	@Inject(at = @At("HEAD"), method = "setupOverlayColor(II)V")
	private static void setupOverlayColorBegin(CallbackInfo info) {
		Annotations.onBegin("GlStateManager.setupOverlayColor");
	}

	@Inject(at = @At("RETURN"), method = "setupOverlayColor(II)V")
	private static void setupOverlayColorEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "teardownOverlayColor()V")
	private static void teardownOverlayColorBegin(CallbackInfo info) {
		Annotations.onBegin("GlStateManager.teardownOverlayColor");
	}

	@Inject(at = @At("RETURN"), method = "teardownOverlayColor()V")
	private static void teardownOverlayColorEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
