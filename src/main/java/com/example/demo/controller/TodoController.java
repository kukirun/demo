package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	//testTodo 생략
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			System.out.println("createTodo");
			//todoentity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			//id를 null로 초기화
			entity.setId(null);
			
			//임시 사용자 아이디 설정.
			entity.setUserId(temporaryUserId);
			
			//서비스 이용해 todo 엔티티 생성
			List<TodoEntity> entities = service.create(entity);
			
			//자바 스트림을 이용해 리턴된 엔티티 리스트를 todoDTO 리스트로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			//변환된 todoDTO리스트를 이용해 responseDTO를 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			//responseDTO 를 리턴
			return ResponseEntity.ok().body(response);
		} catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList() {
		String teporaryUserId = "temporary-user";

		// 서비스 retrieve() 를 이용 Todo 리스트 갯
		List<TodoEntity> entities = service.retrieve(teporaryUserId);

		//스트림 이용 리턴된 앤티티 리스트를 TodoDTO 리스트로 변경
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//변환된 tododto 리스트를 이용 responsedto 초기화
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//responsedto 리턴
		return ResponseEntity.ok().body(response);
	}
	
}
