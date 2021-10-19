/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iviettech.spring_mvc_product.controller;


import com.iviettech.spring_mvc_product.entities.ProductDetailEntity;
import com.iviettech.spring_mvc_product.service.ProductDetailService;
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
public class CardDetailController {

    @Autowired
    private ProductDetailService productDetailService;
    
    @RequestMapping(value = {"/card-detail"}, method = RequestMethod.GET)
    public String cardDetail(Model model, HttpSession session,
            @SessionAttribute("productDetailAddCarts") List<ProductDetailEntity> productDetailAddCarts) {
        model.addAttribute("totalPrice", productDetailService.totalPriceCardDetail(productDetailAddCarts));
        return "card-detail";
    }

    @RequestMapping(value = {"/delete-card-detail/{index}"}, method = RequestMethod.GET)
    public String deletetCardDetail(Model model, HttpSession session,
            @SessionAttribute("productDetailAddCarts") List<ProductDetailEntity> productDetailAddCarts,
            @PathVariable("index") int index) {
        productDetailAddCarts.remove(index);
        return "redirect:/card-detail";
    }
    
    @RequestMapping(value = {"/update-card-detail/{index}"}, method = RequestMethod.POST)
    public String updateQuantitytCardDetail(Model model, HttpSession session,
            @SessionAttribute("productDetailAddCarts") List<ProductDetailEntity> productDetailAddCarts,
            @ModelAttribute("updateQuantity") int updateQuantity,
            @PathVariable("index") int index) {        
        productDetailAddCarts.get(index).setQuantityPurchased(updateQuantity);
        return "redirect:/card-detail";
    }
}
