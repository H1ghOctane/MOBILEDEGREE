package config;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigManager {

    public static final BrowserstackConfig BS_CONFIG =
            ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    public static final LocalConfig LOCAL_CONFIG =
            ConfigFactory.create(LocalConfig.class, System.getProperties());

    private ConfigManager() {
    }
}
