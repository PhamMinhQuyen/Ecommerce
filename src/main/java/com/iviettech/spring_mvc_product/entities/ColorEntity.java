/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.entities;

import com.iviettech.spring_mvc_product.enums.Color;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "color")
public class ColorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Color color;

    

   @OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
    private List<ProductDetailEntity> productDetail;

    public ColorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<ProductDetailEntity> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailEntity> productDetail) {
        this.productDetail = productDetail;
    }

  
    

    
    @Override
    public String toString() {
        return "ColorEntity{" + "id=" + id + ", color=" + color + '}';
    }

}
