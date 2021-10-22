package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;

//@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
	
	/*
	@Autowired
	private OrderGroupApiLogicService service;
	
	@PostConstruct
	public void init() {
		this.baseService = service;
	}
	
	@Override
	@PostMapping("")
	public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("")
	public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
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
