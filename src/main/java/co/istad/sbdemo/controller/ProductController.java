package co.istad.sbdemo.controller;

import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.model.Product;
import co.istad.sbdemo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content) })

    @GetMapping
    ResponseEntity<?> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                   @RequestParam(required = false, defaultValue = "true") Boolean isStock) {
        Map<String, Object> data = Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, isStock));
//        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);

        // Builder Pattern
        return ResponseEntity.accepted().body(data);

    }

//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void crateProduct(@Valid @RequestBody ProductRequest request){
        productService.createNewProduct(request);
    }

    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid, @Valid @RequestBody ProductRequest productRequest){
        productService.editProductByUuid(uuid, productRequest);
    }


//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }



    @GetMapping("/{id}")
    Map<String, Object> findByID(@PathVariable Integer id){
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findByUuid(@PathVariable String uuid){
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }
}
