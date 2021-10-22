package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Users;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;

//@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, Users> {
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
