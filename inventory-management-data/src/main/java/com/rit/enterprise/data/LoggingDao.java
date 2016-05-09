package com.rit.enterprise.data;

import com.rit.enterprise.core.Log;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.time.LocalDateTime;
import java.util.List;

@RegisterMapper(LogMapper.class)
public interface LoggingDao {

    @SqlUpdate("INSERT INTO logging (transaction_id, description, timestamp, product_id, amount_changed) " +
            "VALUES (:transactionId, :description, :timestamp, :productId, :amountChanged)")
    public void insertLogging(@Bind("transactionId") Integer transactionId,
                              @Bind("description") String description,
                              @Bind("timestamp") LocalDateTime timestamp,
                              @Bind("productId") int productId,
                              @Bind("amountChanged") int amountChanged);

    @SqlQuery("SELECT * FROM logging ORDER BY timestamp")
    public List<Log> getLogs();

}
