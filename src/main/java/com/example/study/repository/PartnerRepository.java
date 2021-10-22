package com.example.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

	//select * from partner where category = ?
	List<Partner>findByCategory(Category category);
	
}
