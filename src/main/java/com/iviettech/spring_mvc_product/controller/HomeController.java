package com.iviettech.spring_mvc_product.controller;

import com.iviettech.spring_mvc_product.entities.ColorEntity;
import com.iviettech.spring_mvc_product.entities.ImageEntity;
import com.iviettech.spring_mvc_product.entities.ProductDetailEntity;
import com.iviettech.spring_mvc_product.entities.ProductEntity;
import com.iviettech.spring_mvc_product.entities.SizeEntity;
import com.iviettech.spring_mvc_product.enums.Color;
import com.iviettech.spring_mvc_product.enums.Size;
import com.iviettech.spring_mvc_product.service.CategoryService;
import com.iviettech.spring_mvc_product.service.ColorService;
import com.iviettech.spring_mvc_product.service.ImageService;
import com.iviettech.spring_mvc_product.service.ProductDetailService;

import com.iviettech.spring_mvc_product.service.ProductService;
import com.iviettech.spring_mvc_product.service.SizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("product")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    private ColorService colorService;
    
    @Autowired
    private SizeService sizeService;
    
    @Autowired
    private ProductDetailService productDetailService;

    @RequestMapping(value = {"/home", "/products", "/"}, method = RequestMethod.GET)
    public String getAccounts(Model model, HttpServletRequest request,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("products", productService.getProductsSortCreateDate());
        model.addAttribute("images", imageService.getImages());
        model.addAttribute("type", type);
        model.addAttribute("message", message);
        return "products";
    }

    @RequestMapping("/add-products")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("action", "add");
        return "add-product";
    }

    @RequestMapping("/delete-product/{productId}")
    public String deleteBook(Model model,
            @PathVariable("productId") int productId) {
        if (!productService.deleteProduct(productId)) {
            return "redirect:/home?type=success&message=Delete Product with Id: " + productId + " success!!!";
        } else {
            return "redirect:/home?type=danger&message=Product Book with Id: " + productId + " fail!!!";
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(Model model,
            @ModelAttribute("product") ProductEntity product,
            HttpSession session) {

        List<MultipartFile> files = product.getFiles();
        Date date = new Date();

        List<ImageEntity> images = new ArrayList<ImageEntity>();

        if (files != null && files.size() > 0) {
            for (MultipartFile file : files) {
                ImageEntity image = new ImageEntity();
                try {
                    image.setName(file.getOriginalFilename());
                    images.add(image);
                    String fileName = file.getOriginalFilename();
                    File imageFile = new File(servletContext.getRealPath("/resources/images/"), fileName);
                    file.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.setProduct(product);
            }

            product.setCreateDate(date);
            product.setImages(images);
            productService.save(product);

        }
        ProductDetailEntity productDetail = new ProductDetailEntity();
        productDetail.setProduct(product);
        model.addAttribute("productDetail", productDetail);

        session.setAttribute("product", product);
        session.setAttribute("productDetails", new ArrayList<ProductDetailEntity>());

        model.addAttribute("colors", Color.values());
        model.addAttribute("sizes", Size.values());
        return "add-product-detail";
    }

    @RequestMapping(value = {"/add-product-detail"}, method = RequestMethod.POST)
    public String view(Model model, HttpSession session,
            @ModelAttribute("productDetail") ProductDetailEntity productDetail,
            @SessionAttribute("productDetails") List<ProductDetailEntity> productDetails,
            @SessionAttribute("product") ProductEntity product
    ) {

        for (ProductDetailEntity productDetail1 : productDetails) {

            if (productDetail1.getColor().getColor().name().equals(productDetail.getColor().getColor().name())
                    && productDetail1.getSize().getSize().name().equals(productDetail.getSize().getSize().name())) {
                model.addAttribute("productDetail", new ProductDetailEntity());
                model.addAttribute("colors", Color.values());
                model.addAttribute("sizes", Size.values());
                model.addAttribute("message", "message");
                return "add-product-detail";
            }

        }

        productDetail.setProduct(product);
        productDetails.add(productDetail);
        session.setAttribute("productDetails", productDetails);
        model.addAttribute("colors", Color.values());
        model.addAttribute("sizes", Size.values());
        return "add-product-detail";
    }

    @RequestMapping(value = {"/delete-product-detail/{index}"}, method = RequestMethod.GET)
    public String deletetProductDetail(Model model, HttpServletRequest request,
            @PathVariable("index") int index) {
        List<ProductDetailEntity> productDetails = (List<ProductDetailEntity>) request.getSession().getAttribute("productDetails");
        productDetails.remove(index);
        request.getSession().setAttribute("productDetails", productDetails);
        model.addAttribute("productDetail", new ProductDetailEntity());
        model.addAttribute("colors", Color.values());
        model.addAttribute("sizes", Size.values());
        return "add-product-detail";
    }
    
    @RequestMapping(value = {"/save-product-detail"}, method = RequestMethod.GET)
    public String addAll(Model model, HttpServletRequest request) {

        List<ProductDetailEntity> productDetails = (List<ProductDetailEntity>) request.getSession().getAttribute("productDetails");
        for (ProductDetailEntity productDetail : productDetails) {
            if (colorService.getColorByName((productDetail.getColor().getColor().name())).isEmpty()) {
                ColorEntity color = new ColorEntity();
                color.setColor(Color.valueOf(productDetail.getColor().getColor().name()));
                colorService.saveColor(color);
                productDetail.setColor(color);
            } else {
                ColorEntity color = colorService.getColorByName((productDetail.getColor().getColor().name())).get(0);
                colorService.saveColor(color);
                productDetail.setColor(color);
            }

            if (sizeService.getSizeByName(productDetail.getSize().getSize().name()).isEmpty()) {
                SizeEntity size = new SizeEntity();
                size.setSize(Size.valueOf(productDetail.getSize().getSize().name()));
                sizeService.saveSize(size);
                productDetail.setSize(size);
            } else {
                SizeEntity size = sizeService.getSizeByName(productDetail.getSize().getSize().name()).get(0);
                sizeService.saveSize(size);
                productDetail.setSize(size);
            }

            productDetailService.saveProductDetail(productDetail);
        }
        return "redirect:/home";
    }
}
