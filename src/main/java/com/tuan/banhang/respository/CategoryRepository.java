package com.tuan.banhang.respository;

import com.tuan.banhang.model.category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<category, Integer> {
}
