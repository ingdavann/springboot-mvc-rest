package co.istad.sbdemo.service;

import co.istad.sbdemo.dto.CategoryRequest;
import co.istad.sbdemo.dto.CategoryResponse;

import java.util.List;

public interface CategoriesService {
    void deleteCategoryById(Integer id);
    CategoryResponse editCategoryById(Integer id, CategoryRequest categoryRequest);
    void crateNewCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse findCategoryByName(String name);

}
