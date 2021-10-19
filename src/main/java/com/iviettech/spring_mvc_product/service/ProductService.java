package com.iviettech.spring_mvc_product.service;

import com.iviettech.spring_mvc_product.entities.ProductEntity;
import com.iviettech.spring_mvc_product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getProducts() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public void save(ProductEntity product) {
        productRepository.save(product);
    }

    public List<ProductEntity> getProductsSortCreateDate() {
        return productRepository.findAllByOrderByCreateDateDesc();
    }

    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return productRepository.existsById(id);
    }

    public ProductEntity getProduct(int id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new ProductEntity();
    }
}
