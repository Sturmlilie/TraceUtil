package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public class MixinLevelLoadingScreen {
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
	private void renderBegin(CallbackInfo info) {
		MCAnnotations.onBegin("LevelLoadingScreen.render");
	}

	@Inject(at = @At("RETURN"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
	private void renderEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
