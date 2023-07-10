package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Product name");
        product.setDescription("Product description");
        product.setImgUrl("https://example.com/product.jpg");
        product.setPrice(BigDecimal.valueOf(99.99));

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        verify(productRepository).save(product);

        Assertions.assertEquals(product, createdProduct);
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Product name");
        existingProduct.setDescription("Product description");
        existingProduct.setImgUrl("https://example.com/product.jpg");
        existingProduct.setPrice(BigDecimal.valueOf(99.99));
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        Product retrievedProduct = productService.getProductById(productId);

        verify(productRepository).findById(productId);

        Assertions.assertEquals(existingProduct, retrievedProduct);
    }

    @Test
    public void testGetProductByIdWithInvalidId() {
        Long invalidProductId = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.getProductById(invalidProductId);
        });
    }

    @Test
    public void testGetProductByIdWithNonexistentId() {
        Long nonexistentProductId = 1L;

        when(productRepository.findById(nonexistentProductId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productService.getProductById(nonexistentProductId);
        });
    }


}
