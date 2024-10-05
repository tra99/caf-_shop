package shop.coffee.backend.controller;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.coffee.backend.entity.Category;
import shop.coffee.backend.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>>getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category>getCategoryById(@PathVariable long id){
        Optional<Category> category=categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
            .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory=categoryService.createOrUpdateCategory(category);
        return new ResponseEntity<>(createdCategory,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id,@RequestBody Category updateCategory){
        Optional<Category> exitingCategoryOptional=categoryService.getCategoryById(id);

        if(exitingCategoryOptional.isPresent()){
            Category existingCategory=exitingCategoryOptional.get();
            existingCategory.setCategoryName(updateCategory.getCategoryName());

            Category savedCategory=categoryService.createOrUpdateCategory(existingCategory);
            return new ResponseEntity<>(savedCategory, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByCategoryId(@PathVariable long id){
        Optional<Category> category=categoryService.getCategoryById(id);

        if(category.isPresent()){
            categoryService.deleteCategoryById(id);
            String message="Category with ID "+id+" is deleted";
            return new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
        }  
        else{
            String message="Category with ID "+id+" is deleted";
            return new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
        }  
    }
}
