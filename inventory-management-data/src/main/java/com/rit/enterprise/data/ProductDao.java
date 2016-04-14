package com.rit.enterprise.data;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProductDao {

    @SqlQuery("SELECT quantity FROM inventory WHERE productId = :id")
    public int getStockQuantityForProductId(@Bind("id") int id);

    @SqlUpdate("UPDATE inventory SET quantity = (quantity - :stockQuantity), transactionId = :transactionId WHERE productId = :id")
    public void decreaseStockQuantityForProductId(int id,  int stockQuantity, int transactionId);

    @SqlUpdate("UPDATE inventory SET quantity = (quantity + :stockQuantity), transactionId = :transactionId WHERE productId = :id")
    public void increaseStockQuantityForProductId(int id,  int stockQuantity, int transactionId);

}
