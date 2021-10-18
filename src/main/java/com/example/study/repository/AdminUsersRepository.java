package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.AdminUsers;

@Repository
public interface AdminUsersRepository extends JpaRepository<AdminUsers, Long> {

}
