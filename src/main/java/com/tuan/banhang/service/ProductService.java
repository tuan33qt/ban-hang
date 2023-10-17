package com.tuan.banhang.service;

import com.tuan.banhang.model.Product;
import com.tuan.banhang.model.category;
import com.tuan.banhang.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProduct(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public  void  deleteProduct(int theId) {
        productRepository.deleteById(theId);
    }
    public Optional<Product> findById(int theId) {
        return productRepository.findById(theId);
    }
    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategoryId_Id(id);
    }
}
