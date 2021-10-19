/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.repository;

import com.iviettech.spring_mvc_product.entities.SizeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Integer>{
    
    @Query(value = "select * from size s where s.size = ?1",nativeQuery = true)
    List<SizeEntity> getSizeByName(String name);
    
}
