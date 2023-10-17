package com.tuan.banhang.controller;

import com.tuan.banhang.global.GlobalData;
import com.tuan.banhang.model.Product;
import com.tuan.banhang.respository.ProductRepository;
import com.tuan.banhang.service.CategoryService;
import com.tuan.banhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/shop")
    public String shop(Model model, @Param("keyword") String keyword) {
        List<Product> listproduct=productService.getAllProduct(keyword);
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products",listproduct);
        model.addAttribute("keyword", keyword);
        return "shop";
    }
    @GetMapping("/shop/category")
    public  String shopByCategory(@RequestParam("id") int theId, Model model) {
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProductByCategoryId(theId));
        return "shop";
    }
    @GetMapping("/shop/viewproduct")
    public String viewProduct(@RequestParam("id") int theId,Model model) {
        model.addAttribute("product",productService.findById(theId).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

}
