package com.example.exercise17.Service;

import com.example.exercise17.Model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.exercise17.Repository.CategoryRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository ;

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }


    public void addCategory(Category category) {

        categoryRepository.save(category);
    }

    public boolean updateCategory(Integer id , Category category) {
        Category oldCategory = categoryRepository.getById(id);
        if(oldCategory == null) {
            return false;
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
        return true;
    }


    public boolean deleteCategory(int id) {
        Category category = categoryRepository.getById(id);
        if(category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }
}
