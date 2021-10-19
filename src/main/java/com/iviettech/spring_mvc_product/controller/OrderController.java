/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.controller;

import com.iviettech.spring_mvc_product.entities.ImageEntity;
import com.iviettech.spring_mvc_product.entities.ProductDetailEntity;
import com.iviettech.spring_mvc_product.entities.ProductEntity;
import com.iviettech.spring_mvc_product.service.ImageService;
import com.iviettech.spring_mvc_product.service.ProductDetailService;
import com.iviettech.spring_mvc_product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 *
 * @author ADMIN
 */
@Controller
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/order-product/{productId}")
    public String orderProduct(Model model, HttpSession session,
            @PathVariable("productId") int productId) {
        List<ProductDetailEntity> sizesOfProductDetail = productDetailService.getSizesOfProduct(productId);
        String size = sizesOfProductDetail.get(0).getSize().getSize().name();
        List<ProductDetailEntity> colorsOfProductDetailBySize = productDetailService.getColorsOfProductBySize(productId, size);
        String color = colorsOfProductDetailBySize.get(0).getColor().getColor().name();
        ProductDetailEntity priceOfProductByColordAndSize = productDetailService.getPriceOfProductByColordAndSize(productId, size, color).get(0);
        session.setAttribute("product", productService.getProduct(productId));
        session.setAttribute("images", imageService.getImageByProductId(productId));
        session.setAttribute("sizesOfProductDetail", sizesOfProductDetail);
        model.addAttribute("size", size);
        model.addAttribute("colorsOfProductDetailBySize", colorsOfProductDetailBySize);
        model.addAttribute("priceOfProductByColordAndSize", priceOfProductByColordAndSize);
        model.addAttribute("productDetail", new ProductDetailEntity());
        if (session.getAttribute("productDetailAddCarts") == null) {
            session.setAttribute("productDetailAddCarts", new ArrayList<ProductDetailEntity>());
        }

        return "product-detail";
    }

    @RequestMapping(value = {"/show-items"}, method = RequestMethod.POST)
    public String showColor(Model model, HttpSession session,
            @ModelAttribute("productDetail") ProductDetailEntity produtDetail,
            @SessionAttribute("product") ProductEntity product,
            @SessionAttribute("productDetailAddCarts") List<ProductDetailEntity> productDetailAddCarts,
            @SessionAttribute("images") List<ImageEntity> images
    ) {    
        boolean checkQuantity=true;
         if (produtDetail.getQuantityPurchased() > 0) {
                int quantity = productDetailService.getQuantity(product.getId(),
                        produtDetail.getSize().getSize().name(),
                        produtDetail.getColor().getColor().name()).get(0).getQuantity();
                if (produtDetail.getQuantityPurchased() > quantity) {
                    checkQuantity=false;
                     model.addAttribute("message", "The quantity purchased is more than the quantity in stock");               
                }
            }
        if (produtDetail.getQuantityPurchased() == 0 ||  checkQuantity==false) {
            String size = produtDetail.getSize().getSize().name();
            String setColor = null;
            List<ProductDetailEntity> colorsOfProductDetailBySize = productDetailService.getColorsOfProductBySize(product.getId(), size);
            for (ProductDetailEntity color : colorsOfProductDetailBySize) {
                if (color.getColor().getColor().name().equals(produtDetail.getColor().getColor().name())) {
                    setColor = produtDetail.getColor().getColor().name();
                    break;
                } else {
                    setColor = colorsOfProductDetailBySize.get(0).getColor().getColor().name();
                }
            }
            ProductDetailEntity priceOfProductByColordAndSize = productDetailService.getPriceOfProductByColordAndSize(product.getId(), size, setColor).get(0);
            model.addAttribute("colorsOfProductDetailBySize", colorsOfProductDetailBySize);
            model.addAttribute("priceOfProductByColordAndSize", priceOfProductByColordAndSize);
            model.addAttribute("size", size);
            model.addAttribute("setColor", setColor);
            model.addAttribute("productDetail", new ProductDetailEntity());
            return "product-detail";
        } else {
            produtDetail.setImages(images);
            produtDetail.setQuantity(productDetailService.getQuantity(product.getId(),
                        produtDetail.getSize().getSize().name(),
                        produtDetail.getColor().getColor().name()).get(0).getQuantity());
            productDetailAddCarts.add(produtDetail);
            
            return "redirect:/card-detail";
            
        }
        
       

    }

}
