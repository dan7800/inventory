package com.rit.enterprise.data;

public class ProductDao {

    // This class is a mock data access object.

    public int getStockQuantityForProductId(int id) {
        return 5;
    }

    public void decreaseStockQuantityForProductId(int id, int transactionId, int stockQuantity) {
    }

    public void increaseStockQuantityForProductId(int id, int transactionId, int stockQuantity) {
    }

}
