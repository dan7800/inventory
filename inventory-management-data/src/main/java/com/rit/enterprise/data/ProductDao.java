package com.rit.enterprise.data;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public class ProductDao {

    //@SqlQuery("SELECT quantity FROM inventory WHERE productId = :id")
    public int getStockQuantityForProductId(@Bind("id") String product_id){
        return 5;
    }

    //@SqlUpdate("UPDATE inventory SET quantity = (quantity - :stockQuantity), transactionId = :transactionId WHERE productId = :id")
    public boolean decreaseStockQuantityForProductId(String product_id,  int stockQuantity, long transactionId){
        return true;
    }
    public boolean decreaseStockQuantityForProductId(String product_id,  int stockQuantity){
        return true;
    }

    //@SqlUpdate("UPDATE inventory SET quantity = (quantity + :stockQuantity), transactionId = :transactionId WHERE productId = :id")
    public boolean increaseStockQuantityForProductId(String product_id,  int stockQuantity){
        return true;
    }
    public double getBaseSalesPrice(String product_id){
        return 59.99;
    }
    public double getProductCost(String product_id){
        return 20.00;
    }

}
