package co.istad.sbdemo.service;

import co.istad.sbdemo.dto.ProductEditResponse;
import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createNewProduct(ProductRequest request);
    List<ProductResponse> findProducts(String name, Boolean isStock);
    ProductResponse findProductById(Integer id);
    ProductResponse findProductByUuid(String uuid);
    ProductEditResponse editProductByUuid(String uuid, ProductRequest productRequest);
    void deleteProductByUuid(String uuid);
}
