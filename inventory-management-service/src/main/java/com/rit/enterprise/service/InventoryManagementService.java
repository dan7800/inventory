package com.rit.enterprise.service;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.rit.enterprise.config.InventoryManagementConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class InventoryManagementService extends Application<InventoryManagementConfiguration> {

    private static final String AUTO_CONFIG_PACKAGE = "com.rit.enterprise";

    public static void main(String[] args) throws Exception {
        new InventoryManagementService().run(args);
    }

    @Override
    public void initialize(Bootstrap<InventoryManagementConfiguration> bootstrap) {
        GuiceBundle<InventoryManagementConfiguration> guiceBundle = GuiceBundle.<InventoryManagementConfiguration>newBuilder()
                .addModule(new InventoryManagementServiceGuiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(InventoryManagementConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(InventoryManagementConfiguration configuration, Environment environment) throws Exception {

    }
}
