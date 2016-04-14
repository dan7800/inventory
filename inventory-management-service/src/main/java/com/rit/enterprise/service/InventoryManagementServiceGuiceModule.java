package com.rit.enterprise.service;

import com.google.inject.AbstractModule;
import com.rit.enterprise.data.InventoryManagementDataGuiceModule;

public class InventoryManagementServiceGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new InventoryManagementDataGuiceModule());
    }
}
