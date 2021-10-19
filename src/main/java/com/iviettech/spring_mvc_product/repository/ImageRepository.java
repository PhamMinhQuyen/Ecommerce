package com.iviettech.spring_mvc_product.repository;


import com.iviettech.spring_mvc_product.entities.ImageEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
      List<ImageEntity> findByProduct_Id(int id);
}
