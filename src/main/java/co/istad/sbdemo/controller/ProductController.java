package co.istad.sbdemo.controller;

import co.istad.sbdemo.dto.ProductEditRequest;
import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void crateProduct(@Valid @RequestBody ProductRequest request){
        System.out.println(request);
        productService.createNewProduct(request);
    }
    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid, @RequestBody ProductEditRequest request){
        productService.editProductByUuid(uuid, request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }

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

    @GetMapping("/{id}")
    Map<String, Object> findByID(@PathVariable Integer id){
        return Map.of(
                "message", "Product has been found",
                "data", productService.findByID(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findByUuid(@PathVariable String uuid){
        return Map.of(
                "message", "Product has been found",
                "data", productService.findByUuid(uuid)
        );
    }
}
