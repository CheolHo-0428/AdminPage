package com.example.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr,Long>{

	// select * from userr where account = ?
	Optional<Userr> findByAccount(String account);
	
	// select * from userr where email=?
	Optional<Userr> findByEmail(String email);
	
	// select * from userr where account=? and email=?
	Optional<Userr> findByAccountAndEmail(String account, String email);
}
