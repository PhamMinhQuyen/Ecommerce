/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.service;

import com.iviettech.spring_mvc_product.entities.ColorEntity;
import com.iviettech.spring_mvc_product.repository.ColorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<ColorEntity> getColorByName(String color) {
        return colorRepository.getColorByName(color);
    }

    public void saveColor(ColorEntity color) {
        colorRepository.save(color);
    }
}
