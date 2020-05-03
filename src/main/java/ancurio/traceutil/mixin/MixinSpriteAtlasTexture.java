package ancurio.traceutil.mixin;

import ancurio.traceutil.anno.MCAnnotations;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpriteAtlasTexture.class)
public class MixinSpriteAtlasTexture {
	@Inject(at = @At("HEAD"), method = "tickAnimatedSprites()V")
	private void tickAnimatedSpritesBegin(CallbackInfo info) {
		MCAnnotations.onBegin("SpriteAtlasTexture.tickAnimatedSprites");
	}

	@Inject(at = @At("RETURN"), method = "tickAnimatedSprites()V")
	private void tickAnimatedSpritesEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
