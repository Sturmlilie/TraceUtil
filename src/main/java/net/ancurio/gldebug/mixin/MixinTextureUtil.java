package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.MCAnnotations;
import net.minecraft.client.texture.TextureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureUtil.class)
public class MixinTextureUtil {
	@Inject(at = @At("HEAD"), method = "prepareImage(Lnet/minecraft/client/texture/NativeImage$GLFormat;IIII)V")
	private static void prepareImageBegin(CallbackInfo info) {
		MCAnnotations.onBegin("TextureUtil.prepareImage");
	}

	@Inject(at = @At("RETURN"), method = "prepareImage(Lnet/minecraft/client/texture/NativeImage$GLFormat;IIII)V")
	private static void drawEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
