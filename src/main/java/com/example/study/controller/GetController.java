package com.example.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;

@RestController
@RequestMapping("/api")
public class GetController {
	
	
	@RequestMapping(path = "/getMethod", method = RequestMethod.GET)
	public String getRequest() {
		
		return "Hi getRequest!";
	}
	
	
	@GetMapping("/getParameter") //localhost:8080/api/getParameter?id=againnr&pwd=12345
	public String getParameter(@RequestParam(name="idd") String id, 
				@RequestParam(name="password") String pwd) {
		String idd = "againnr1";
		String password = "0852";
		
		
		System.out.println("id: " + id);
		System.out.println("pwd: " + pwd);
		return id + " " + pwd;
	}
	
	@GetMapping("/getMultiParameter")
	public SearchParam getMultiParameter(SearchParam searchParam) {
		System.out.println(searchParam.getAccount());
		System.out.println(searchParam.getEmail());
		System.out.println(searchParam.getPage());
		
		return searchParam;
	}
	
	@GetMapping("/header")
	public Header getHeader() {
		
		return Header.builder().resultCode("OK").description("OK").build();
	}
}
