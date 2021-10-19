/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.service;

import com.iviettech.spring_mvc_product.entities.ProductDetailEntity;
import com.iviettech.spring_mvc_product.repository.ProductDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    public void saveProductDetail(ProductDetailEntity productDetail) {
        productDetailRepository.save(productDetail);
    }

    public List<ProductDetailEntity> getSizesOfProduct(int productId) {
        return productDetailRepository.getSizesOfProduct(productId);
    }

    public List<ProductDetailEntity> getColorsOfProductBySize(int productId,String size) {
        return productDetailRepository.getColorsOfProductBySize(productId,size);
    }
    
    public List<ProductDetailEntity> getPriceOfProductByColordAndSize(int productId,String size,String color) {
        return productDetailRepository.getPriceOfProductByColordAndSize(productId,size,color);
    }
    
    
    public List<ProductDetailEntity> getQuantity(int productId,String size,String color) {
        return productDetailRepository.getQuantity(productId,size,color);
    }
    
    public double totalPriceCardDetail(List<ProductDetailEntity> productDetail){
        double totalPrice=0;
        for (ProductDetailEntity productDetailEntity : productDetail) {
            totalPrice=totalPrice+(productDetailEntity.getQuantityPurchased()*productDetailEntity.getPrice());
        }   
        return totalPrice;     
    }
    
    


}
