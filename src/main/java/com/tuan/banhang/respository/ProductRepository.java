package com.tuan.banhang.respository;

import com.tuan.banhang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
   List<Product> findAllByCategoryId_Id(int id);
   @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
   public List<Product> search(String keyword);
}
