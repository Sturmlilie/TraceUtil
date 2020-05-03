package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.MCAnnotations;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public class MixinLevelLoadingScreen {
	@Inject(at = @At("HEAD"), method = "render(IIF)V")
	private void renderBegin(CallbackInfo info) {
		MCAnnotations.onBegin("LevelLoadingScreen.render");
	}

	@Inject(at = @At("RETURN"), method = "render(IIF)V")
	private void renderEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
