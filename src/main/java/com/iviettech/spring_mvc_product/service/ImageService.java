package com.iviettech.spring_mvc_product.service;


import com.iviettech.spring_mvc_product.entities.ImageEntity;
import com.iviettech.spring_mvc_product.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageEntity> getImages(){
        return (List<ImageEntity>) imageRepository.findAll();
    }

    public void save(ImageEntity image){
        imageRepository.save(image);
    }
    
    public List<ImageEntity> getImageByProductId(int id) {
        return imageRepository.findByProduct_Id(id);
    }
}
