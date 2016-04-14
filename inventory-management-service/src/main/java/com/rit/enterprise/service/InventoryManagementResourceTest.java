package com.rit.enterprise.service;

import com.rit.enterprise.service.InventorManagementResource;
import com.rit.enterprise.db.InventoryDAO;
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
        when(dao.getStockQuantityForProductId(1)).thenReturn(5);

        final int response = resources.client().target("/inventory/product-stock/1")
                .request().get();

        verify(dao).getStockQuantitityForProductId(1);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/decrease/{$id}/{$amount} endpoint
     */
    @Test
    public void decreaseStockQuantityForProductId() throws Exception {
        when(dao.decreaseStockQuantityForProductId(1, 0, 2)).thenReturn(3);

        final int response = resources.client().target("/inventory/product-stock/decrease/1/2")
                .request().get();

        verify(dao).decreaseStockQuantityForProductId(1, 0, 2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
    
    /**
     * Tests the /inventory/product-stock/increase/{$id}/{$amount} endpoint
     */
    @Test
    public void increaseStockQuantityForProductId() throws Exception {
        when(dao.increaseStockQuantityForProductId(1, 0, 2)).thenReturn(7);

        final int response = resources.client().target("/inventory/product-stock/increase/1/2")
                .request().get();

        verify(dao).increaseStockQuantityForProductId(1, 0, 2);
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

}
