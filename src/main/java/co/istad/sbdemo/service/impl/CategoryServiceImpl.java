package co.istad.sbdemo.service.impl;

import co.istad.sbdemo.dto.CategoryRequest;
import co.istad.sbdemo.dto.CategoryResponse;
import co.istad.sbdemo.model.Category;
import co.istad.sbdemo.repository.CategoryRepository;
import co.istad.sbdemo.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategoryById(Integer id) {
        // check category if exist
        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found"
            );
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest categoryRequest) {
        // load old Category
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Category has not found"
                ));
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        categoryRepository.save(category);
        return this.findCategoryById(id);
    }

    @Override
    public void crateNewCategory(CategoryRequest categoryRequest) {

        // check name conflict
        if (categoryRepository.existsByName(categoryRequest.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exist"
            );
        }
        // DTO Pattern
        Category category = new Category();
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getName(),
                        category.getDescription()
                )).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not found!"
                ));
        return new CategoryResponse(category.getName(), category.getDescription());
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }

}
