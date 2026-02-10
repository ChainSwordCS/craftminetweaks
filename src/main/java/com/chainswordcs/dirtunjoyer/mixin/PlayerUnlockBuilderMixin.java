package com.chainswordcs.dirtunjoyer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Shadow;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import java.util.Optional;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.AssetInfo;

import net.minecraft.aprilfools.PlayerUnlock;
//import net.minecraft.aprilfools.PlayerUnlock.Builder;

@Mixin(PlayerUnlock.Builder.class)
public abstract class PlayerUnlockBuilderMixin {
	
	@Accessor("exclusiveKey")
	public abstract String getExclusiveKey();
	@Accessor("exclusiveKey")
	public abstract void setExclusiveKey(String exclusiveKey);
	
	@Inject(at = @At("TAIL"), method = "<init>")
	private void BuilderPostInject(String $$0, Optional<RegistryEntry<PlayerUnlock>> $$1, Optional<AssetInfo> $$2, CallbackInfo ci) {
		// If exclusiveKey is empty, create a new one specific to this Unlock.
		// So this Unlock is in a group alone by itself.
		// That enables the Active/Inactive toggle without the
		// mutual-exclusive logic interering with the desired behavior.
		if(getExclusiveKey().equals("")) {
			setExclusiveKey($$0);
		}
	}
}
