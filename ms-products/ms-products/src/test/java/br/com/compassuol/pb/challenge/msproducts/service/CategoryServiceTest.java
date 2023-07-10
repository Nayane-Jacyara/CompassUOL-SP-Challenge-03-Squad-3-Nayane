package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Category name");

        when(categoryRepository.save(category)).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        verify(categoryRepository).save(category);

        Assertions.assertEquals(category, createdCategory);
    }

    @Test
    public void testGetCategoryById() {

        Long categoryId = 1L;

        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Category name");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        Category retrievedCategory = categoryService.getCategoryById(categoryId);

        verify(categoryRepository).findById(categoryId);

        Assertions.assertEquals(existingCategory, retrievedCategory);
    }

    @Test
    public void testGetCategoryByIdWithInvalidId() {
        Long invalidCategoryId = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            categoryService.getCategoryById(invalidCategoryId);
        });
    }

    @Test
    public void testGetCategoryByIdWithNonexistentId() {
        Long nonexistentCategoryId = 1L;

        when(categoryRepository.findById(nonexistentCategoryId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            categoryService.getCategoryById(nonexistentCategoryId);
        });
    }



}
