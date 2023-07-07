package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        // Verificar se os dados do produto são válidos
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        // Verificar se o ID é válido
        if (id == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo.");
        }

        // Obter o produto pelo ID usando o repositório
        Optional<Product> productOptional = productRepository.findById(id);

        // Verificar se o produto existe
        if (productOptional.isEmpty()) {
            throw new NoSuchElementException("Produto não encontrado para o ID: " + id);
        }

        return productOptional.get();
    }

    public Page<Product> getProducts(int page, int linesPerPage, String direction, String orderBy) {
        // Verificar se os parâmetros de paginação e ordenação são válidos
        if (page < 1) {
            throw new IllegalArgumentException("O número da página deve ser maior ou igual a 1.");
        }
        if (linesPerPage < 1) {
            throw new IllegalArgumentException("O número de linhas por página deve ser maior ou igual a 1.");
        }

        // Criar objeto Pageable com a página, linhas por página e ordenação
        PageRequest pageable = PageRequest.of(page - 1, linesPerPage, Sort.Direction.fromString(direction), orderBy);

        // Obter a página de produtos usando o repositório
        Page<Product> productPage = productRepository.findAll(pageable);

        // Retornar a página de produtos
        return productPage;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        // Verificar se o produto existe
        Product existingProduct = getProductById(id);

        // Atualizar os atributos do produto existente com os valores do novo produto
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImgUrl(updatedProduct.getImgUrl());
        existingProduct.setPrice(updatedProduct.getPrice());

        // Salvar o produto atualizado no banco de dados
        return productRepository.save(existingProduct);
    }


    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
