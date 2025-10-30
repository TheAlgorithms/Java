package org.thealgorithms.config;

/**
 * Demonstrates how to use ConfigLoader with a shared YAML file.
 */
public class ExampleUsage {
    public static void main(String[] args) {
        ConfigLoader loader = new ConfigLoader("config.yaml");

        System.out.println("Global DB URL: " + loader.getGlobal("db_url"));
        System.out.println("Service A JWT Secret: " + loader.getServiceOverride("serviceA", "jwt_secret"));
        System.out.println("Service B DB URL: " + loader.getServiceOverride("serviceB", "db_url"));
    }
}
