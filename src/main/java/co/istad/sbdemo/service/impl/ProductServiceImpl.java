package co.istad.sbdemo.service.impl;

import co.istad.sbdemo.dto.ProductEditResponse;
import co.istad.sbdemo.dto.ProductRequest;
import co.istad.sbdemo.dto.ProductResponse;
import co.istad.sbdemo.model.Category;
import co.istad.sbdemo.model.Product;
import co.istad.sbdemo.repository.ProductRepository;
import co.istad.sbdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public void createNewProduct(ProductRequest request) {
        Product newProduct = new Product();
         newProduct.setName(request.name());
         newProduct.setPrice(request.price());
         newProduct.setQty(request.qty());
         newProduct.setUuid(UUID.randomUUID().toString());
         newProduct.setCreateAt(LocalDateTime.now());
         newProduct.setIsStock(true);
         productRepository.save(newProduct);
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean isStock) {
        List<Product> products = productRepository.findAll();
        return products.stream()
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
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product has not found"
                ));
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty()
                );
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not found"
            );

        }
        Product product = productRepository.findProductByUuid(uuid);
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty()
        );
    }

    @Override
    public ProductEditResponse editProductByUuid(String uuid, ProductRequest productRequest) {
        // load old product
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product has not found"
            );
        }
        Product product = productRepository.findProductByUuid(uuid);
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setQty(productRequest.qty());
        productRepository.save(product);
        return new ProductEditResponse(
                product.getName(),
                product.getPrice(),
                product.getQty()
        );
    }


    @Override
    public void deleteProductByUuid(String uuid) {
        if (!productRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product has not found"
            );
        }
        Product product = this.productRepository.findProductByUuid(uuid);
        productRepository.deleteById(product.getId());

    }
}
