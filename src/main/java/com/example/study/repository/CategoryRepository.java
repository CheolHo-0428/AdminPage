package com.example.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Category;
import com.example.study.model.enums.CategoryType;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	//select * from category where type = ?
	Optional<Category> findByType(CategoryType type);

	

}
