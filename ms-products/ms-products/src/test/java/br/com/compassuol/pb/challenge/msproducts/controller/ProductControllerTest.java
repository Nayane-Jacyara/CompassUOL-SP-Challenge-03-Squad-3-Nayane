package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    public void testGetProductById() {

        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        when(productService.getProductById(productId)).thenReturn(existingProduct);

        ResponseEntity<Product> responseEntity = productController.getProductById(productId);

        verify(productService).getProductById(productId);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(existingProduct, responseEntity.getBody());
    }

    @Test
    public void testGetProductByIdWithNonexistentId() {
        Long nonexistentProductId = 1L;

        when(productService.getProductById(nonexistentProductId)).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productController.getProductById(nonexistentProductId);
        });

        verify(productService).getProductById(nonexistentProductId);
    }

}
