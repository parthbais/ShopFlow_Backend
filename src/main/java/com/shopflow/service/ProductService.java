package com.shopflow.service;

import com.shopflow.dto.request.ProductRequest;
import com.shopflow.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    Page<ProductResponse> getAllProducts(int page, int size);
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
