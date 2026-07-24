package me.pepperbell.continuity.client.resource;

import net.minecraft.resources.Identifier;

import java.util.Map;

public interface SpriteLoaderStitchContext {
	ThreadLocal<SpriteLoaderStitchContext> THREAD_LOCAL = new ThreadLocal<>();

	Map<Identifier, Identifier> getEmissiveIdMap();

	void setHasEmissives(boolean hasEmissives);
}
