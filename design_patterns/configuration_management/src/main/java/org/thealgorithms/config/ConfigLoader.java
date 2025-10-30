package org.thealgorithms.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

/**
 * ConfigLoader reads configuration from a shared YAML file.
 * It supports global and service-specific configurations.
 */
public class ConfigLoader {

    private final Map<String, Object> config;

    public ConfigLoader(String filename) {
        Yaml yaml = new Yaml();
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream(filename)) {
            if (in == null) {
                throw new IllegalArgumentException("Configuration file not found: " + filename);
            }
            config = yaml.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the value of a global config key.
     */
    public Object getGlobal(String key) {
        Map<String, Object> global = (Map<String, Object>) config.get("global");
        return global != null ? global.get(key) : null;
    }

    /**
     * Returns a value for a specific service override.
     */
    public Object getServiceOverride(String service, String key) {
        Map<String, Map<String, Object>> services =
            (Map<String, Map<String, Object>>) config.get("services");
        if (services == null || !services.containsKey(service)) {
            return null;
        }
        return services.get(service).getOrDefault(key, getGlobal(key));
    }
}
