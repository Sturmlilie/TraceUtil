package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.render.DiffuseLighting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DiffuseLighting.class)
public class MixinDiffuseLighting {
	@Inject(at = @At("HEAD"), method = "enable()V")
	private static void enableBegin(CallbackInfo info) {
		Annotations.onBegin("DiffuseLighting.enable");
	}

	@Inject(at = @At("RETURN"), method = "enable()V")
	private static void enableEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "disable()V")
	private static void disableBegin(CallbackInfo info) {
		Annotations.onBegin("DiffuseLighting.disable");
	}

	@Inject(at = @At("RETURN"), method = "disable()V")
	private static void disableEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "enableForLevel(Lnet/minecraft/client/util/math/Matrix4f;)V")
	private static void enableForLevelBegin(CallbackInfo info) {
		Annotations.onBegin("DiffuseLighting.enableForLevel");
	}

	@Inject(at = @At("RETURN"), method = "enableForLevel(Lnet/minecraft/client/util/math/Matrix4f;)V")
	private static void enableForLevelEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "enableGuiDepthLighting()V")
	private static void enableGuiDepthLightingBegin(CallbackInfo info) {
		Annotations.onBegin("DiffuseLighting.enableGuiDepthLighting");
	}

	@Inject(at = @At("RETURN"), method = "enableGuiDepthLighting()V")
	private static void enableGuiDepthLightingEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "disableGuiDepthLighting()V")
	private static void disableGuiDepthLightingBegin(CallbackInfo info) {
		Annotations.onBegin("DiffuseLighting.disableGuiDepthLighting");
	}

	@Inject(at = @At("RETURN"), method = "disableGuiDepthLighting()V")
	private static void disableGuiDepthLightingEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
