package me.pepperbell.continuity.api.client;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Properties;

public interface CtmProperties extends Comparable<CtmProperties> {
	Collection<Identifier> getSpriteDependencies();

	interface Factory<T extends CtmProperties> {
		@Nullable
		T createProperties(Properties properties, Identifier resourceId, PackResources pack, int packPriority, ResourceManager resourceManager, String method);
	}
}
