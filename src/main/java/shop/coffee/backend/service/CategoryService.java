package shop.coffee.backend.service;

import java.util.*;
import shop.coffee.backend.entity.Category;
import shop.coffee.backend.repository.CategoryRepository;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(long id){
        return categoryRepository.findById(id);
    }

    public Category createOrUpdateCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(long id){
        categoryRepository.deleteById(id);
    }
}
