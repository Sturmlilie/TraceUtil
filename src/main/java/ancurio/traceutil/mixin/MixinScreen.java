package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class MixinScreen {
	@Inject(at = @At("HEAD"), method = "render(IIF)V")
	private void renderBegin(CallbackInfo info) {
		MCAnnotations.onBegin("Screen.render");
	}

	@Inject(at = @At("RETURN"), method = "render(IIF)V")
	private void renderEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
