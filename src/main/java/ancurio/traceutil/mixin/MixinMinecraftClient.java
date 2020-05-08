package ancurio.traceutil.mixin;

import ancurio.traceutil.Main;
import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.DisableableProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
	@Shadow
	private DisableableProfiler profiler;

	@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/RunArgs;)V")
	private void onClientInitialized(CallbackInfo info) {
		MCAnnotations.clientProfiler = profiler;
		// Any point where a GL context is already created (but before acutal rendering) is fine
		Main.onGlContextValid();
	}
}
