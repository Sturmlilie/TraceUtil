package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NativeImage.class)
public class MixinNativeImage {
	@Inject(at = @At("HEAD"), method = "upload(IIIIIIIZZZZ)V")
	private void uploadBegin(CallbackInfo info) {
		MCAnnotations.onBegin("NativeImage.upload");
	}

	@Inject(at = @At("RETURN"), method = "upload(IIIIIIIZZZZ)V")
	private void uploadEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
