package me.pepperbell.continuity.client.resource;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface AtlasLoaderLoadContext {
	ThreadLocal<AtlasLoaderLoadContext> THREAD_LOCAL = new ThreadLocal<>();

	void setEmissiveIdMap(@Nullable Map<ResourceLocation, ResourceLocation> map);
}
