package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.texture.TextureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureUtil.class)
public class MixinTextureUtil {
	@Inject(at = @At("HEAD"), method = "allocate(Lnet/minecraft/client/texture/NativeImage$GLFormat;IIII)V")
	private static void prepareImageBegin(CallbackInfo info) {
		MCAnnotations.onBegin("TextureUtil.allocate");
	}

	@Inject(at = @At("RETURN"), method = "allocate(Lnet/minecraft/client/texture/NativeImage$GLFormat;IIII)V")
	private static void drawEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
