package me.pepperbell.continuity.client.resource;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public interface SpriteLoaderStitchContext {
	ThreadLocal<SpriteLoaderStitchContext> THREAD_LOCAL = new ThreadLocal<>();

	Map<ResourceLocation, ResourceLocation> getEmissiveIdMap();

	void markHasEmissives();
}
