package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import com.example.demo.model.TodoService;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping
	public String testController() {
		return "hello boot";
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testservice();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/testGetMapping")
	public String test2() {
		return "hello boot getmapping";
	}
	
	@GetMapping("/{id}")
	public String test3(@PathVariable(required = false) String id) {
		return "hello test getmapping"+id;
	}
	
	@GetMapping("/testRequestBody")
	public String testRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "hi id " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}

	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("hello resDTO");
		ResponseDTO<String> reponse = ResponseDTO.<String>builder().data(list).build();
		
		return reponse;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("hello resEntity you got 400");
		ResponseDTO<String> reponse = ResponseDTO.<String>builder().data(list).build();
		// http status를 400으로 설정
//		return ResponseEntity.badRequest().body(reponse);
		return ResponseEntity.ok().body(reponse);
	}
}
