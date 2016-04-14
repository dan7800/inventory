package com.rit.enterprise.service;

import com.google.inject.Inject;
import com.rit.enterprise.data.ProductDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/inventory")
public class InventoryManagementResource {

    private final ProductDao productDao;

    @Inject
    public InventoryManagementResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    @Path("/product-stock/{id}")
    public int getStockQuantityById(@PathParam("id") String id) {
        return productDao.getStockQuantityForProductId(id);
    }

    @POST
    @Path("/product-stock/decrease/{id}/{amount}")
    public Response decreaseStockQuantiyByAmount(@PathParam("id") String id,
                                                 @PathParam("amount") int amount,
                                                 @QueryParam("transactionId") long transactionId) {
        productDao.decreaseStockQuantityForProductId(id, amount, transactionId);
        return Response.ok().build();
    }

    @POST
    @Path("/product-stock/increase/{id}/{amount}")
    public Response increaseStockQuantiyByAmount(@PathParam("id") String id,
                                                 @PathParam("amount") int amount) {
        productDao.increaseStockQuantityForProductId(id, amount);
        return Response.ok().build();
    }
    @GET
    @Path("/product-price/{id}")
    public double getBaseSalesPrice(@PathParam("id") String id) {
        return productDao.getBaseSalesPrice(id);
    }
    @GET
    @Path("/product-cost/{id}")
    public double getProductCost(@PathParam("id") String id) {
        return productDao.getProductCost(id);
    }

}
