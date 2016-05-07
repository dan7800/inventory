package com.rit.enterprise.data;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ProductDao {

    @SqlQuery("SELECT amount FROM inventory WHERE product = :id")
    public Integer getStockQuantityForProductId(@Bind("id") int productId);

    @SqlUpdate("UPDATE inventory SET amount = (amount - :amountChanged) WHERE product = :productId")
    public void decreaseStockQuantityForProductId(@Bind("productId") int productId, @Bind("amountChanged") int amountChanged);

    @SqlUpdate("UPDATE inventory SET amount = (amount + :amountChanged) WHERE product = :productId")
    public void increaseStockQuantityForProductId(@Bind("productId") int productId, @Bind("amountChanged") int amountChanged);

    @SqlQuery("SELECT base_price FROM inventory WHERE product = :id")
    public Double getBasePriceForProductId(@Bind("id") int id);

    @SqlQuery("SELECT base_cost FROM inventory WHERE product = :id")
    public Double getBaseCostForProductId(@Bind("id") int id);

}
