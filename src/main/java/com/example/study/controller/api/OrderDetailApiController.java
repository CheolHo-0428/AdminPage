package com.example.study.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;

//@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
	
	/*
	@Autowired
	private OrderDetailApiLogicService service;
	
	@PostConstruct //위 필드값이 동작하기전에 동작
	public void init() {
		this.baseService = service;
	}
	
	@Override
	@PostMapping("")
	public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {
		log.info("{}", request);
		return service.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<OrderDetailApiResponse> read(@PathVariable Long id) {
		log.info("read id: {}", id);
		return service.read(id);
	}

	@Override
	@PutMapping("")
	public Header<OrderDetailApiResponse> update(@RequestBody Header<OrderDetailApiRequest> request) {
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
