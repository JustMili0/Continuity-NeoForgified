package me.pepperbell.continuity.client.mixin;

import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StateHolder.class)
public interface StateHolderAccessor<O, S> {
	@Invoker("getNullableValue")
	@Nullable
	<T extends Comparable<T>> T continuity$getNullableValue(Property<T> property);
}
