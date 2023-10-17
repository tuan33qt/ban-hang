package com.tuan.banhang.controller;

import com.tuan.banhang.dto.ProductDTO;
import com.tuan.banhang.model.Product;
import com.tuan.banhang.model.category;
import com.tuan.banhang.respository.CustomerRepository;
import com.tuan.banhang.service.CategoryService;
import com.tuan.banhang.service.CustomerService;
import com.tuan.banhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @GetMapping("/admin")
    public String admin() {
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
        model.addAttribute("category",new category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("admin/categories/update")
    public String updateCat(@RequestParam("id") int theId,Model model) {
        Optional<category> theCategory=categoryService.findByIdCategory(theId);
        if(theCategory.isPresent()) {
            model.addAttribute("category", theCategory.get());
            return "categoriesAdd";
        }else {
            return "404";
        }
    }
    @GetMapping("admin/categories/delete")
    public String deleteCat(@RequestParam("id") int theId) {
        categoryService.deleteByIdCategory(theId);
        return "redirect:/admin/categories";
    }
    //PRODUCT

    @GetMapping("/admin/products")
    public String getAllProduct(Model model,@Param("keyword") String keyword) {
        model.addAttribute("products",productService.getAllProduct(keyword));
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String AddProduct(Model model) {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String postProductAdd(@ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
    @RequestParam("imgName")String imgName) throws Exception{
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setProduct_id(productDTO.getProduct_id());
        product.setCost_price(productDTO.getCost_price());
        product.setCurrent_quantity(productDTO.getCurrent_quantity());
        product.setDescription(productDTO.getDescription());
        product.setCategoryId(categoryService.findByIdCategory(productDTO.getCategoryId()).get());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath= Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }else  {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("admin/product/delete")
    public String deleteProduct(@RequestParam("id") int theId) {
        productService.deleteProduct(theId);
        return "redirect:/admin/products";
    }
    @GetMapping("admin/product/update")
    public String updateProduct(@RequestParam("id") int theId,Model model) {
        Product product=productService.findById(theId).get();
        ProductDTO productDTO=new ProductDTO();
        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setName(product.getName());
        productDTO.setCost_price(product.getCost_price());
        productDTO.setCurrent_quantity(product.getCurrent_quantity());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryId(product.getCategoryId().getId());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("productDTO",productDTO);
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @GetMapping("/admin/user")
    public String getAllUser(Model model) {
        model.addAttribute("user",customerService.getAllCustomer());
        return "user";
    }
    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("userId")int theId) {
        customerService.deleteById(theId);
        return "redirect:/admin/user";
    }
}
