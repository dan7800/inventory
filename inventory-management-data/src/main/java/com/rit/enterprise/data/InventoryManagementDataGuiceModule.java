package com.rit.enterprise.data;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.rit.enterprise.config.InventoryManagementGuiceModule;
import org.skife.jdbi.v2.DBI;

public class InventoryManagementDataGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new InventoryManagementGuiceModule());
    }

    @Provides
    public ProductDao providesProductDao(DBI jdbi) {
        return jdbi.onDemand(ProductDao.class);
    }

    @Provides
    public LoggingDao providesLoggingDao(DBI jdbi) {
        return jdbi.onDemand(LoggingDao.class);
    }
}
