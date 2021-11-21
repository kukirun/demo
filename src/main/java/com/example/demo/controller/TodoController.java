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
	
	//testTodo ����
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			System.out.println("createTodo");
			//todoentity�� ��ȯ
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			//id�� null�� �ʱ�ȭ
			entity.setId(null);
			
			//�ӽ� ����� ���̵� ����.
			entity.setUserId(temporaryUserId);
			
			//���� �̿��� todo ��ƼƼ ����
			List<TodoEntity> entities = service.create(entity);
			
			//�ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� todoDTO ����Ʈ�� ��ȯ
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			//��ȯ�� todoDTO����Ʈ�� �̿��� responseDTO�� �ʱ�ȭ
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			//responseDTO �� ����
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

		// ���� retrieve() �� �̿� Todo ����Ʈ ��
		List<TodoEntity> entities = service.retrieve(teporaryUserId);

		//��Ʈ�� �̿� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ����
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//��ȯ�� tododto ����Ʈ�� �̿� responsedto �ʱ�ȭ
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//responsedto ����
		return ResponseEntity.ok().body(response);
	}
	
}
