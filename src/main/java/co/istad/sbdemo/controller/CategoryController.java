package co.istad.sbdemo.controller;

import co.istad.sbdemo.dto.CategoryRequest;
import co.istad.sbdemo.dto.CategoryResponse;
import co.istad.sbdemo.model.Product;
import co.istad.sbdemo.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content) })


    @GetMapping
    List<CategoryResponse> findCategories(){
        return categoryService.findCategories();
    }

    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.crateNewCategory(categoryRequest);
    }

    @GetMapping("/{id}")
    CategoryResponse findCategoryById(@PathVariable Integer id){
        return categoryService.findCategoryById(id);
    }

    @DeleteMapping("/{id}")
    void deleteCategoryById(Integer id){
        categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id, @Valid @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategoryById(id,categoryRequest);
    }

}
