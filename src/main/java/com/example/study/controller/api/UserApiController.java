package com.example.study.controller.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Users;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, Users> {
	
	@Override
	@GetMapping("")
	public Header<List<UserApiResponse>> search(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10)
			Pageable pageable) {
		
		log.info("{}", pageable);
		return baseService.search(pageable);
	}
	

	/*
	@Autowired
	private UserApiLogicService service;
	
	@PostConstruct
	public void init() {
		this.baseService = service;
	}
	
	
	@Override
	@PostMapping("") // api/user
	public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}") // api/user/{id}
	public Header<UserApiResponse> read(@PathVariable Long id) {
		log.info("read id : {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("") // api/user
	public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
		log.info("{}", request);
		return service.update(request);
	}

	@Override
	@DeleteMapping("{id}") //api/user/{id}
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return service.delete(id);
	}
	*/
	
	
}
