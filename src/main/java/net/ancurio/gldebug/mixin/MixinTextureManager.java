package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class MixinTextureManager {
	@Inject(at = @At("HEAD"), method = "tick()V")
	private void tickBegin(CallbackInfo info) {
		Annotations.onTextureManagerTickBegin();
	}
	
	@Inject(at = @At("RETURN"), method = "tick()V")
	private void tickEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
