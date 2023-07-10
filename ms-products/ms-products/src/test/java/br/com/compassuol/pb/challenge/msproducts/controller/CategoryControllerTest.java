package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    public void testGetCategoryByIdWithNonexistentId() {

        Long nonexistentCategoryId = 1L;

        when(categoryService.getCategoryById(nonexistentCategoryId)).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            categoryController.getCategoryById(nonexistentCategoryId);
        });

        verify(categoryService).getCategoryById(nonexistentCategoryId);
    }

    @Test
    public void testGetAllCategories() {

        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories();

        verify(categoryService).getAllCategories();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(categories, responseEntity.getBody());
    }

}
