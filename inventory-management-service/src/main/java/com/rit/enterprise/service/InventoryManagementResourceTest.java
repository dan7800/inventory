package com.rit.enterprise.service;

import com.rit.enterprise.service.InventoryManagementResource;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryManagementResourceTest {
	private static final ProductDao dao = mock(ProductDao.class);
	
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new InventoryManagementResource(dao))
            .build();
//    @Captor
//    private ArgumentCaptor<Person> personCaptor;
//    private Person person;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        reset(dao);
    }
    
    /**
     * Tests the /inventory/product-stock/{$id} endpoint
     */
    @Test
    public void getStockQuantityById() throws Exception {
        when(dao.getStockQuantityForProductId("Test")).thenReturn(5);

        final Response response = resources.client().target("/inventory/product-stock/Test")
                .request().get();

        verify(dao).getStockQuantityForProductId("Test");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/decrease/{$id}/{$amount} endpoint
     */
    @Test
    public void decreaseStockQuantityForProductId() throws Exception {
        when(dao.decreaseStockQuantityForProductId("Test", 0, 2)).thenReturn(true);

        final Response response = resources.client().target("/inventory/product-stock/decrease/Test/2")
                .request().get();

        verify(dao).decreaseStockQuantityForProductId("Test", 0, 2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/increase/{$id}/{$amount} endpoint
     */
    @Test
    public void increaseStockQuantityForProductId() throws Exception {
        when(dao.increaseStockQuantityForProductId("Test", 0)).thenReturn(true);

        final Response response = resources.client().target("/inventory/product-stock/increase/Test/2")
                .request().get();

        verify(dao).increaseStockQuantityForProductId("Test", 0);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-price/{$id} endpoint
     */
    @Test
    public void getBaseSalesPrice() throws Exception {
        when(dao.getBaseSalesPrice("Test")).thenReturn(59.99);

        final Response response = resources.client().target("/inventory/product-price/Test")
                .request().get();

        verify(dao).getBaseSalesPrice("Test");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-cost/{$id} endpoint
     */
    @Test
    public void getProductCost() throws Exception {
        when(dao.getProductCost("Test")).thenReturn(20.00);

        final Response response = resources.client().target("/inventory/product-cost/Test")
                .request().get();

        verify(dao).getProductCost("Test");
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    @Test
    public void reportProcurement() throws Exception {
        when(dao.reportProcurement("Test",20.0)).thenReturn((long)1234567890);
        verify(dao).reportProcurement("Test",20.0);
    }

}
