package com.tuan.banhang.controller;

import com.tuan.banhang.global.GlobalData;
import com.tuan.banhang.model.Product;
import com.tuan.banhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalData.cart.add(productService.findById(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String CartGet(Model model) {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("cart",GlobalData.cart);
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getCost_price).sum());
        return "cart";
    }
    @GetMapping("/checkout")
    public String checkOut() {
        return "redirect:/shop";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String deleteToCart(@PathVariable int index) {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

}
