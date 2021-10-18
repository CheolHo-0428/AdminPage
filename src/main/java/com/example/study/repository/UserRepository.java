package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

	//select * from users where phone_number = ? order by id desc
	Users findByPhoneNumberOrderByIdDesc(String phoneNumber);
}
