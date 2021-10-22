package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.AdminUsers;
import com.example.study.model.network.request.AdminUsersApiRequest;
import com.example.study.model.network.response.AdminUsersApiResponse;

//@Slf4j
@RestController
@RequestMapping("/api/adminUsers")
public class AdminUsersApiController extends CrudController<AdminUsersApiRequest, AdminUsersApiResponse, AdminUsers> {
	
	/*
	@Autowired
	private AdminUsersApiLogicService service;
	
	@PostConstruct
	public void init() {
		this.baseService = service;
	}
	
	@Override
	@PostMapping("")
	public Header<AdminUsersApiResponse> create(@RequestBody Header<AdminUsersApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<AdminUsersApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("")
	public Header<AdminUsersApiResponse> update(@RequestBody Header<AdminUsersApiRequest> request) {
		log.info("{}", request);
		return service.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		log.info("delete id: {}", id);
		return service.delete(id);
	}
	*/
	
	
}
