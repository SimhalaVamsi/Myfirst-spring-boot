package com.ecommerce.project.controller;

import com.ecommerce.project.models.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api") //This will make all the requests from the of /api forward to CategoryController Class and it is called class level request handling by the controller.
public class CategoryController {
    //Rather creating constructor we can use annotation called Autowired
    @Autowired
    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping("/public/categories")
    //@RequestMapping(value="/api/public/categories",method = RequestMethod.GET)
    //This RequestMapping is equivalent to GetMapping, Rather denoting like GetMapping we can use RequestMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        categoryService.createCategories(category);
        return new ResponseEntity<>("Category is successfully created",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategroy(@PathVariable Long categoryId){
        try{
            String status=categoryService.deleteCategories(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new  ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Category with categoryId "+categoryId,HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}
