package ancurio.traceutil.mixin;

import ancurio.traceutil.trace.MCAnnotations;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
	@Inject(at = @At("HEAD"), method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V")
	private void renderItemBegin(CallbackInfo info) {
		MCAnnotations.onBegin("ItemRenderer.renderItem");
	}

	@Inject(at = @At("RETURN"), method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V")
	private void renderItemEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}

	@Inject(at = @At("HEAD"), method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V")
	private void renderGuiItemModelBegin(CallbackInfo info) {
		MCAnnotations.onBegin("ItemRenderer.renderGuiItemModel");
	}

	@Inject(at = @At("RETURN"), method = "renderGuiItemModel(Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/render/model/BakedModel;)V")
	private void renderGuiItemModelEnd(CallbackInfo info) {
		MCAnnotations.onGenericEnd();
	}
}
