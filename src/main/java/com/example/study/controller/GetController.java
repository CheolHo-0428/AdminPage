package com.example.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetController {
	
	
	@RequestMapping(path = "/getMethod", method = RequestMethod.GET)
	public String getRequest() {
		
		return "Hi getRequest!";
	}
}
