package me.pepperbell.continuity.api.client;

import me.pepperbell.continuity.impl.client.ProcessingDataKeyRegistryImpl;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

@ApiStatus.NonExtendable
public interface ProcessingDataKeyRegistry {
	static ProcessingDataKeyRegistry get() {
		return ProcessingDataKeyRegistryImpl.INSTANCE;
	}

	default <T> ProcessingDataKey<T> registerKey(Identifier id, Supplier<T> valueSupplier) {
		return registerKey(id, valueSupplier, null);
	}

	<T> ProcessingDataKey<T> registerKey(Identifier id, Supplier<T> valueSupplier, Consumer<T> valueResetAction);

	@Nullable
	ProcessingDataKey<?> getKey(Identifier id);

	int getRegisteredAmount();
}
