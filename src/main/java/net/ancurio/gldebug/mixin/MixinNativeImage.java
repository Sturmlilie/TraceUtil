package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NativeImage.class)
public class MixinNativeImage {
	@Inject(at = @At("HEAD"), method = "upload(IIIIIIIZZZZ)V")
	private void uploadBegin(CallbackInfo info) {
		Annotations.onNativeImageUploadBegin();
	}
	
	@Inject(at = @At("RETURN"), method = "upload(IIIIIIIZZZZ)V")
	private void uploadEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
