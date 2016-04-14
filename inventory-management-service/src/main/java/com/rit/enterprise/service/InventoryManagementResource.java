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
    public int getStockQuantityById(@PathParam("id") int id) {
        return productDao.getStockQuantityForProductId(id);
    }

    @POST
    @Path("/product-stock/decrease/{id}/{amount}")
    public Response decreaseStockQuantiyByAmount(@PathParam("id") int id,
                                                 @PathParam("amount") int amount,
                                                 @QueryParam("transactionId") int transactionId) {
        productDao.decreaseStockQuantityForProductId(id, amount, transactionId);
        return Response.ok().build();
    }

    @POST
    @Path("/product-stock/decrease/{id}/{amount}")
    public Response increaseStockQuantiyByAmount(@PathParam("id") int id,
                                                 @PathParam("amount") int amount,
                                                 @QueryParam("transactionId") int transactionId) {
        productDao.increaseStockQuantityForProductId(id, amount, transactionId);
        return Response.ok().build();
    }

}
