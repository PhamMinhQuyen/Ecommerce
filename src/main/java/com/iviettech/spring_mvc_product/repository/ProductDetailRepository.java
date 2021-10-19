/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.repository;

import com.iviettech.spring_mvc_product.entities.ProductDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * from product_detail as pd\n"
            + "join product as p \n"
            + "on pd.product_id = p.id\n"
            + "join size as s on pd.size_id = s.id\n"
            + "where pd.product_id = ?1 group by pd.size_id")
    List<ProductDetailEntity> getSizesOfProduct(int id);

    @Query(nativeQuery = true, value = "select * from product_detail as pd\n"
            + "join product as p on pd.product_id=p.id\n"
            + "join size as s on pd.size_id=s.id\n"
            + "join color as c on pd.color_id=c.id\n"
            + "where pd.product_id=?1 and s.size=?2\n"
            + "group by pd.color_id")
    List<ProductDetailEntity> getColorsOfProductBySize(int id, String size);

    @Query(nativeQuery = true, value = "select * from product_detail as pd\n"
            + "join product as p on pd.product_id=p.id\n"
            + "join size as s on pd.size_id=s.id\n"
            + "join color as c on pd.color_id=c.id\n"
            + "where pd.product_id=?1 and s.size=?2 and c.color=?3")
    List<ProductDetailEntity> getPriceOfProductByColordAndSize(int id, String size, String color);

    @Query(nativeQuery = true, value = "select * from product_detail as pd\n"
            + "join product as p on pd.product_id=p.id\n"
            + "join size as s on pd.size_id=s.id\n"
            + "join color as c on pd.color_id=c.id\n"
            + "where pd.product_id=?1 and s.size=?2 and c.color=?3")
    List<ProductDetailEntity> getQuantity(int id, String size, String color);
}
