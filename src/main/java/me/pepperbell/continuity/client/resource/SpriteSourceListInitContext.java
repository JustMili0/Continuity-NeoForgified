package me.pepperbell.continuity.client.resource;

import net.minecraft.resources.Identifier;

import java.util.Set;

public interface SpriteSourceListInitContext {
	ThreadLocal<SpriteSourceListInitContext> THREAD_LOCAL = new ThreadLocal<>();

	Set<Identifier> getExtraIds();
}
