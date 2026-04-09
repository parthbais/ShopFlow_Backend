package com.shopflow.service.impl;

import com.shopflow.dto.request.ProductRequest;
import com.shopflow.dto.response.ProductResponse;
import com.shopflow.entity.Product;
import com.shopflow.exception.ResourceNotFoundException;
import com.shopflow.mapper.ProductMapper;
import com.shopflow.repository.ProductRepository;
import com.shopflow.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper     productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product saved = productRepository.save(productMapper.toEntity(request));
        log.info("Product created: id={}, name={}", saved.getId(), saved.getName());
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productMapper.toResponse(findOrThrow(id));
    }

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size))
                .map(productMapper::toResponse);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = findOrThrow(id);
        productMapper.updateEntity(product, request);
        log.info("Product updated: id={}", id);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        findOrThrow(id);
        productRepository.deleteById(id);
        log.info("Product deleted: id={}", id);
    }

    private Product findOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }
}
