package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {
	@Inject(at = @At("HEAD"), method = "render(IIF)V")
	private void renderBegin(CallbackInfo info) {
		Annotations.onBegin("Screen.render");
	}

	@Inject(at = @At("RETURN"), method = "render(IIF)V")
	private void renderEnd(CallbackInfo info) {
		Annotations.onGenericEnd();
	}
}
