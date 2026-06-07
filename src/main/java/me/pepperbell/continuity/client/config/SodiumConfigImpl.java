package me.pepperbell.continuity.client.config;

import java.util.Objects;
import me.pepperbell.continuity.client.ContinuityClient;
import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.option.OptionBinding;
import net.caffeinemc.mods.sodium.api.config.option.OptionFlag;
import net.caffeinemc.mods.sodium.api.config.structure.BooleanOptionBuilder;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.minecraft.network.chat.Component;

public class SodiumConfigImpl implements ConfigEntryPoint {
    public void registerConfigLate(ConfigBuilder builder) {
        ContinuityConfig config = ContinuityConfig.INSTANCE;

        builder.registerOwnModOptions()
            .setNonTintedIcon(ContinuityClient.asId("icon.png"))
            .addPage(builder.createOptionPage()
                .setName(Component.translatable(ContinuityConfigScreen.getTranslationKey("title")))

                .addOption(newOption(builder, config, config.connectedTextures)
                    .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD))
                .addOption(newOption(builder, config, config.emissiveTextures)
                    .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD))
                .addOption(newOption(builder, config, config.customBlockLayers)
                    .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD))
            );
    }

    private static BooleanOptionBuilder newOption(ConfigBuilder builder, ContinuityConfig config, Option.BooleanOption option) {
        String translationKey = ContinuityConfigScreen.getTranslationKey(option.getKey());
        Objects.requireNonNull(config);

        return builder.createBooleanOption(ContinuityClient.asId(option.getKey()))
            .setName(Component.translatable(translationKey))
            .setTooltip(Component.translatable(ContinuityConfigScreen.getTooltipKey(translationKey)))
            .setDefaultValue(option.get())
            .setBinding(new OptionBindingImpl<>(option))
            .setStorageHandler(config::save);
    }

    private record OptionBindingImpl<T>(Option<T> option) implements OptionBinding<T> {

        public void save(T value) {
                this.option.set(value);
            }

            public T load() {
                return this.option.get();
            }
        }
}