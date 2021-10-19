package com.iviettech.spring_mvc_product.repository;

import com.iviettech.spring_mvc_product.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByOrderByCreateDateDesc();
}
