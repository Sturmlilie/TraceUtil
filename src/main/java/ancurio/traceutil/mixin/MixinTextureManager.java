package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class MixinTextureManager {
	@Inject(at = @At("HEAD"), method = "tick()V")
	private void tickBegin(CallbackInfo info) {
		MCAnnotations.onBegin("TextureManager.tick");
	}

	@Inject(at = @At("RETURN"), method = "tick()V")
	private void tickEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}

	@Inject(at = @At("RETURN"), method = "registerTexture(Lnet/minecraft/util/Identifier;Lnet/minecraft/client/texture/AbstractTexture;)V")
	private void onRegisterTexture(Identifier id, AbstractTexture texture, CallbackInfo info) {
		MCAnnotations.onTextureRegistered(id, texture);
	}
}
