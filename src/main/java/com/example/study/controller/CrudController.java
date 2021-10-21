package com.example.study.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CrudController<Req, Res> implements CrudInterface<Req, Res> {
	
	protected CrudInterface<Req, Res> baseService;
	
	@Override
	@PostMapping("")
	public Header<Res> create(@RequestBody Header<Req> request) {
		log.info("{}", request);
		return baseService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<Res> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return baseService.read(id);
	}

	@Override
	@PutMapping("")
	public Header<Res> update(@RequestBody Header<Req> request) {
		log.info("{}", request);
		return baseService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return baseService.delete(id);
	}
	
	
}