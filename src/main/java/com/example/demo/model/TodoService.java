package com.example.demo.model;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class TodoService {
	public String testservice() {
		return "Test Service";
	}
	
}