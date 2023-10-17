package com.tuan.banhang.respository;

import com.tuan.banhang.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
//    @Query("SELECT username, password, 1 as enabled FROM customers WHERE username = ?")
//    public Customers login(String username);
}
