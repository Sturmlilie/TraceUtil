package ancurio.traceutil.mixin;

import ancurio.traceutil.trace.MCAnnotations;
import java.util.function.Supplier;
import net.minecraft.util.profiler.DisableableProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisableableProfiler.class)
public class MixinDisableableProfiler {
	@Inject(at = @At("HEAD"), method = "push(Ljava/lang/String;)V")
	private void push(String location, CallbackInfo info) {
		if (this == MCAnnotations.clientProfiler) {
			MCAnnotations.onProfilerPush(location);
		}
	}
	
	@Inject(at = @At("HEAD"), method = "push(Ljava/util/function/Supplier;)V")
	private void push(Supplier<String> supplier, CallbackInfo info) {
		if (this == MCAnnotations.clientProfiler) {
			MCAnnotations.onProfilerPush((String) supplier.get());
		}
	}
	
	@Inject(at = @At("HEAD"), method = "pop()V")
	private void pop(CallbackInfo info) {
		if (this == MCAnnotations.clientProfiler) {
			MCAnnotations.onProfilerPop();
		}
	}
	
	@Inject(at = @At("HEAD"), method = "swap(Ljava/lang/String;)V")
	private void pop(String location, CallbackInfo info) {
		if (this == MCAnnotations.clientProfiler) {
			MCAnnotations.onProfilerSwap(location);
		}
	}
	
	@Inject(at = @At("HEAD"), method = "swap(Ljava/util/function/Supplier;)V")
	private void pop(Supplier<String> supplier, CallbackInfo info) {
		if (this == MCAnnotations.clientProfiler) {
			MCAnnotations.onProfilerSwap((String) supplier.get());
		}
	}
}
