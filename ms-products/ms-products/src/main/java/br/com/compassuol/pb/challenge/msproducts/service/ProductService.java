package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        validateProductData(product);

        product.setDate(LocalDateTime.now());

        return productRepository.save(product);
    }


    public Product getProductById(Long id) {
        validateProductId(id);

        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new NoSuchElementException("Produto não encontrado para o ID: " + id);
        }

        return productOptional.get();
    }

    public Page<Product> getProducts(int page, int linesPerPage, String direction, String orderBy) {
        validatePageParameters(page, linesPerPage);

        PageRequest pageable = PageRequest.of(page - 1, linesPerPage, Sort.Direction.fromString(direction), orderBy);

        return productRepository.findAll(pageable);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImgUrl(updatedProduct.getImgUrl());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    private void validateProductData(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
    }

    private void validateProductId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo.");
        }
    }

    private void validatePageParameters(int page, int linesPerPage) {
        if (page < 1) {
            throw new IllegalArgumentException("O número da página deve ser maior ou igual a 1.");
        }
        if (linesPerPage < 1) {
            throw new IllegalArgumentException("O número de linhas por página deve ser maior ou igual a 1.");
        }
    }
}
