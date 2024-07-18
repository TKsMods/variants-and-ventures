package com.faboslav.variantsandventures.fabric.mixin;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.client.render.entity.GelidEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.MurkEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.ThicketEntityRenderer;
import com.faboslav.variantsandventures.common.client.render.entity.VerdantEntityRenderer;
import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import com.faboslav.variantsandventures.common.events.client.RegisterSkullModelEvent;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class SkullBlockEntityRendererMixin
{
	private static ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> variantsandventures$builder = ImmutableMap.builder();

	@Inject(
		at = @At("RETURN"),
		method = "getModels",
		cancellable = true
	)
	private static void variantsandventures$getModels(
		EntityModelLoader modelLoader,
		CallbackInfoReturnable<Map<SkullBlock.SkullType, SkullBlockEntityModel>> cir
	) {
		variantsandventures$builder = ImmutableMap.builder();
		variantsandventures$builder.putAll(cir.getReturnValue());
		RegisterSkullModelEvent.EVENT.invoke(new RegisterSkullModelEvent(SkullBlockEntityRendererMixin::variantsandventures$addModel, modelLoader));
		cir.setReturnValue(variantsandventures$builder.build());
	}

	private static void variantsandventures$addModel(
		SkullBlock.SkullType skullType,
		SkullBlockEntityModel skullBlockEntityModel
	) {
		variantsandventures$builder.put(skullType, skullBlockEntityModel);
	}
}
