package me.pepperbell.continuity.api.client;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public interface CachingPredicates {
	boolean affectsSprites();

	boolean affectsSprite(TextureAtlasSprite sprite);

	boolean affectsBlockStates();

	boolean affectsBlockState(BlockState state);

	boolean isValidForMultipass();

	interface Factory<T extends CtmProperties> {
		CachingPredicates createPredicates(T properties, Function<Identifier, TextureAtlasSprite> spriteGetter);
	}
}
