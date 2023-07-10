package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        // Verificar se o nome da categoria é válido
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        // Verificar se o ID é válido
        if (id == null) {
            throw new IllegalArgumentException("ID da categoria não pode ser nulo.");
        }

        // Obter a categoria pelo ID usando o repositório
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        // Verificar se a categoria existe
        if (categoryOptional.isEmpty()) {
            throw new NoSuchElementException("Categoria não encontrada para o ID: " + id);
        }

        return categoryOptional.get();
    }

    public List<Category> getAllCategories() {
        // Obter todas as categorias usando o repositório
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        // Verificar se a categoria existe
        Category existingCategory = getCategoryById(id);

        // Atualizar os atributos da categoria existente com os valores da nova categoria
        existingCategory.setName(updatedCategory.getName());

        // Salvar a categoria atualizada no banco de dados
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}