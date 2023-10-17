package com.tuan.banhang.service;

import com.tuan.banhang.model.Product;
import com.tuan.banhang.model.category;
import com.tuan.banhang.respository.CategoryRepository;
import com.tuan.banhang.respository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tuan.banhang.respository.ProductRepository.*;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    public List<category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public void addCategory(category category) {
        categoryRepository.save(category);
    }
    public Optional<category> findByIdCategory(int theId) {
        return categoryRepository.findById(theId);
    }
    public void deleteByIdCategory(int theId) {
        categoryRepository.deleteById(theId);
    }

}
