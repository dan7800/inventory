package com.rit.enterprise.service;

import com.rit.enterprise.service.InventoryManagementResource;
import com.rit.enterprise.data.LoggingDao;
import com.rit.enterprise.data.ProductDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryManagementResourceTest {
	private static final ProductDao pdao = mock(ProductDao.class);
	private static final LoggingDao ldao = mock(LoggingDao.class);
	
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new InventoryManagementResource(pdao,ldao))
            .build();
//    @Captor
//    private ArgumentCaptor<Person> personCaptor;
//    private Person person;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        reset(pdao);
        reset(ldao);
    }
    
    /**
     * Tests the /inventory/product-stock/{$id} endpoint
     */
    @Test
    public void getStockQuantityById() throws Exception {
        when(pdao.getStockQuantityForProductId(555)).thenReturn(0);

        final Response response = resources.client().target("/inventory/product-stock/555")
                .request().get();

        verify(pdao).getStockQuantityForProductId(555);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    @Test
    public void getStockQuantityByIdException() throws Exception {
        when(pdao.getStockQuantityForProductId(-50)).thenReturn(-1);

        final Response response = resources.client().target("/inventory/product-stock/-50")
                .request().get();

        verify(pdao).getStockQuantityForProductId(-50);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    
    /**
     * Tests the /inventory/product-stock/decrease/{$id}/{$amount} endpoint
     */
    @Test
    public void decreaseStockQuantityForProductId() throws Exception {
        final Response response = resources.client().target("/inventory/product-stock/decrease/555/2")
                .request().get();

        verify(pdao).decreaseStockQuantityForProductId(555, 2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/increase/{$id}/{$amount} endpoint
     */
    @Test
    public void increaseStockQuantityForProductId() throws Exception {
        
        final Response response = resources.client().target("/inventory/product-stock/increase/555/2")
                .request().get();

        verify(pdao).increaseStockQuantityForProductId(555, 2);
        assert(pdao.getStockQuantityForProductId(555)==2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/increase/{$id}/{$amount} endpoint
     */
    @Test
    public void increaseStockQuantityForProductIdException() throws Exception {
        final Response response = resources.client().target("/inventory/product-stock/increase/555/-2")
                .request().get();

        verify(pdao).increaseStockQuantityForProductId(555, -2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-price/{$id} endpoint
     */
    @Test
    public void getBaseSalesPrice() throws Exception {
        when(pdao.getBasePriceForProductId(555)).thenReturn(59.99);

        final Response response = resources.client().target("/inventory/product-price/555")
                .request().get();

        verify(pdao).getBasePriceForProductId(555);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    /**
     * Tests the /inventory/product-price/{$id} endpoint
     */
    @Test
    public void getBaseSalesPriceException() throws Exception {
        when(pdao.getBasePriceForProductId(-50)).thenReturn(-1.0);

        final Response response = resources.client().target("/inventory/product-price/-50")
                .request().get();

        verify(pdao).getBasePriceForProductId(0);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-cost/{$id} endpoint
     */
    @Test
    public void getBaseCostForProductId() throws Exception {
        when(pdao.getBaseCostForProductId(555)).thenReturn(20.00);

        final Response response = resources.client().target("/inventory/product-cost/555")
                .request().get();

        verify(pdao).getBaseCostForProductId(0);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    /**
     * Tests the /inventory/product-cost/{$id} endpoint
     */
    @Test
    public void getBaseCostForProductIdException() throws Exception {
        when(pdao.getBaseCostForProductId(-50)).thenReturn(-1.00);

        final Response response = resources.client().target("/inventory/product-cost/Test")
                .request().get();

        verify(pdao).getBaseCostForProductId(0);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    
    @Test
    public void insertLogging() throws Exception {
    	LocalDateTime logtime = LocalDateTime.now();
        ldao.insertLogging(00001,"Test",logtime,0,0);
        verify(ldao).insertLogging(00001,"Test",logtime,0,0);
    }

}
