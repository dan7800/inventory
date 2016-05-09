package com.rit.enterprise.service;

import com.google.inject.Inject;
import com.rit.enterprise.core.Log;
import com.rit.enterprise.core.Product;
import com.rit.enterprise.data.LoggingDao;
import com.rit.enterprise.data.ProductDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;


@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryManagementResource {

    private final ProductDao productDao;
    private final LoggingDao loggingDao;

    HttpClient httpClient = HttpClients.createDefault();
    HttpPost   httpPost = new HttpPost("http://vm343a.se.rit.edu/accounting/inventory");

    @Inject
    public InventoryManagementResource(ProductDao productDao, LoggingDao loggingDao) {
        this.productDao = productDao;
        this.loggingDao = loggingDao;
    }

    @GET
    @Path("/product-stock/{id}")
    public int getStockQuantityById(@PathParam("id") int id) {
        return productDao.getStockQuantityForProductId(id);
    }

    @POST
    @Path("/product-stock/decrease/{id}/{amount}")
    public Response decreaseStockQuantiyByAmount(@PathParam("id") int id,
                                                 @PathParam("amount") int decreaseAmount,
                                                 @QueryParam("transactionId") Integer transactionId) {
        if ((productDao.getStockQuantityForProductId(id) - decreaseAmount) >= 0) {
            productDao.decreaseStockQuantityForProductId(id, decreaseAmount);
            loggingDao.insertLogging(transactionId, "Decrease", LocalDateTime.now(), id, decreaseAmount);
            return Response.ok().build();
        } else {
            return Response.notModified("Not enough stock").build();
        }
    }

    @POST
    @Path("/product-stock/increase/{id}/{amount}")
    public Response increaseStockQuantiyByAmount(@PathParam("id") int id,
                                    @PathParam("amount") int increaseAmount,
                                    @QueryParam("transactionId") Integer transactionId) {
        productDao.increaseStockQuantityForProductId(id, increaseAmount);
        loggingDao.insertLogging(transactionId, "Increase", LocalDateTime.now(), id, increaseAmount);
        return Response.ok().build();
    }

    @GET
    @Path("/product-price/{id}")
    public Double getBaseSalesPriceForProductId(@PathParam("id") int id) {
        Double basePrice = productDao.getBasePriceForProductId(id);
        return basePrice != null ? basePrice : -1;
    }

    @GET
    @Path("/product-cost/{id}")
    public double getBaseCostForProductId(@PathParam("id") int id) {
        Double baseCost = productDao.getBaseCostForProductId(id);
        return baseCost != null ? baseCost : -1;
    }

    @GET
    @Path("/logging")
    public List<Log> getLogs() {
        return loggingDao.getLogs();
    }

    @GET
    @Path("/products")
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @POST
    @Path("/purchase/{id}/{amount}")
    public HttpResponse purchaseParts(@PathParam("id") int id,
                                    @PathParam("amount") int increaseAmount
                                    @QueryParam("transactionId") Integer transactionId){
        productDao.increaseStockQuantityForProductId(id, increaseAmount);
        loggingDao.insertLogging(transactionId, "Purchase", LocalDateTime.now(), id, increaseAmount);
        double costOfGoods = productDao.getBaseCostForProductId(id) * amount;
        InventoryRequest request = new InventoryRequest(costOfGoods, transactionId);
        httpPost.setEntity(new URLEncodedFormEntity(request, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        return response;
    }
}
