package net.ancurio.gldebug.mixin;

import net.ancurio.gldebug.trace.mc.Annotations;
import net.minecraft.client.render.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelLoader.class)
public class MixinModelLoader {
	@Inject(at = @At("HEAD"), method = "upload(Lnet/minecraft/client/texture/TextureManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/client/render/model/SpriteAtlasManager;")
	private void uploadBegin(CallbackInfoReturnable info) {
		Annotations.onBegin("ModelLoader.upload");
	}

	@Inject(at = @At("RETURN"), method = "upload(Lnet/minecraft/client/texture/TextureManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/client/render/model/SpriteAtlasManager;")
	private void uploadEnd(CallbackInfoReturnable info) {
		Annotations.onGenericEnd();
	}
}
