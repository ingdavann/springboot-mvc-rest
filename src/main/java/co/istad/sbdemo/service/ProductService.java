package co.istad.sbdemo.service;

import co.istad.sbdemo.dto.ProductEditRequest;
import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createNewProduct(ProductRequest request);
    List<ProductResponse> findProducts(String name, Boolean isStock);
    ProductResponse findByID(Integer id);
    ProductResponse findByUuid(String uuid);
    void editProductByUuid(String uuid, ProductEditRequest request);
    void deleteProductByUuid(String uuid);
}
