package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.gui.DrawableHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawableHelper.class)
public class MixinDrawableHelper {
	@Inject(at = @At("HEAD"), method = "fill(Lnet/minecraft/client/util/math/Matrix4f;IIIII)V")
	private static void fillBegin(CallbackInfo info) {
		Annotations.onDrawableHelperFillBegin();
	}
	
	@Inject(at = @At("RETURN"), method = "fill(Lnet/minecraft/client/util/math/Matrix4f;IIIII)V")
	private static void fillEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
