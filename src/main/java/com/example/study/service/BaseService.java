package com.example.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.study.ifs.CrudInterface;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res>{
	
	@Autowired(required = false) // 빈 객체가 존재 하지 않더라도 예외처리를 발생시키지 않는다.
	protected JpaRepository<Entity, Long> baseRepo;
	
	// 위는 JpaRepository<Item, Long> baseRepo === ItemRepository itemRepository
	
	

}
