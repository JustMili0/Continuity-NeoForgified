package me.pepperbell.continuity.client.resource;

import net.minecraft.resources.Identifier;

import java.util.Map;

public interface SpriteSourceListListContext {
	ThreadLocal<SpriteSourceListListContext> THREAD_LOCAL = new ThreadLocal<>();

	void setEmissiveIdMap(Map<Identifier, Identifier> map);
}
