package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.gui.DrawableHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawableHelper.class)
public class MixinDrawableHelper {
	@Inject(at = @At("HEAD"), method = "fill(Lnet/minecraft/util/math/Matrix4f;IIIII)V")
	private static void fillBegin(CallbackInfo info) {
		MCAnnotations.onBegin("DrawableHelper.fill");
	}

	@Inject(at = @At("RETURN"), method = "fill(Lnet/minecraft/util/math/Matrix4f;IIIII)V")
	private static void fillEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
