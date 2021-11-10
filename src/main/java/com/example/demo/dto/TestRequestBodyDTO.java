package com.example.demo.dto;

import java.util.List;

import com.example.demo.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TestRequestBodyDTO {
	private int id;
	private String message;

}
