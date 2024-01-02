package io.github.com.carnine.config;

import org.aeonbits.owner.ConfigCache;

/**
 * @author mcarnine3
 */
public final class ConfigurationManager {

    private ConfigurationManager() {}

    public static Configuration config() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
