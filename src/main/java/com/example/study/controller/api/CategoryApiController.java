package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Category;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;

//@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {
	
	/*
	@Autowired
	private CategoryApiLogicService service;
	
	@PostConstruct
	public void init(){
		this.baseService = service;
	}
	
	
	@Override
	@PostMapping("")
	public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<CategoryApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("")
	public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
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
