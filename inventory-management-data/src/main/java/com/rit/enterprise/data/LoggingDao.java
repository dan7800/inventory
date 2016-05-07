package com.rit.enterprise.data;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.time.LocalDateTime;

public interface LoggingDao {

    @SqlUpdate("INSERT INTO logging (transaction_id, description, timestamp, product_id, amount_changed) " +
            "VALUES (:transactionId, :description, :timestamp, :productId, :amountChanged)")
    public void insertLogging(@Bind("transactionId")  Integer transactionId, @Bind("description") String description, @Bind("timestamp") LocalDateTime timestamp, @Bind("productId") int productId, @Bind("amountChanged") int amountChanged);

}

