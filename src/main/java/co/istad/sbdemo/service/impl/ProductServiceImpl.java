package co.istad.sbdemo.service.impl;

import co.istad.sbdemo.dto.ProductEditRequest;
import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.dto.ProductResponse;
import co.istad.sbdemo.model.Product;
import co.istad.sbdemo.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("iPhone 14 Pro Max");
        p1.setPrice(1300.0);
        p1.setQty(1);
        p1.setCreateAt(LocalDateTime.now());
        p1.setIsStock(true);
        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("macBook M3 RAM 30GB");
        p2.setPrice(2600.0);
        p2.setQty(2);
        p2.setCreateAt(LocalDateTime.now());
        p2.setIsStock(true);
        products.add(p1);
        products.add(p2);
    }


    @Override
    public void createNewProduct(ProductRequest request) {
        Product newProduct = new Product();
         newProduct.setName(request.name());
         newProduct.setPrice(request.price());
         newProduct.setQty(request.qty());
         newProduct.setUuid(UUID.randomUUID().toString());
         newProduct.setCreateAt(LocalDateTime.now());
         newProduct.setId(products.size()+1);
         newProduct.setIsStock(true);
         products.add(newProduct);
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean isStock) {
        return products
                .stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getIsStock().equals(isStock))
                .map(
                        product -> new ProductResponse(
                                product.getUuid(),
                                product.getName(),
                                product.getPrice(),
                                product.getQty()
                        )
                )
                .toList();
    }

    @Override
    public ProductResponse findByID(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id) && product.getIsStock()
                        .equals(true))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }

    @Override
    public ProductResponse findByUuid(String uuid) {
        return products.stream()
                .filter(product -> product.getUuid().contains(uuid) && product.getIsStock().equals(true)
                ).map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }

    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        long count =  products.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct-> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected Row : " + count);
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        products.removeIf(delete -> delete.getUuid().equals(uuid));
    }
}
