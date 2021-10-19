/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.service;

import com.iviettech.spring_mvc_product.entities.SizeEntity;
import com.iviettech.spring_mvc_product.repository.SizeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class SizeService {
    
    @Autowired
    private SizeRepository sizeRepository;

    public List<SizeEntity> getSizeByName(String size) {
        return sizeRepository.getSizeByName(size);
    }

    public void saveSize(SizeEntity size) {
        sizeRepository.save(size);
    }
}
