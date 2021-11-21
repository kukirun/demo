package com.example.demo.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;
	
	public String testservice() {
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("first item").build();
		//TodoEntity 저장
		repository.save(entity);
		//TodoEntity 검색
		TodoEntity savedEntity = repository.test(entity.getId()).get();
		
		
		return savedEntity.getTitle();
	}
	
	public List<TodoEntity> create(final TodoEntity entity){
		//Validations
		validate(entity);
		
		repository.save(entity);
		
		log.info("entity id : {} is saved",entity.getId());
		
		return repository.findByUserId(entity.getUserId());
	}
	
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("entity cannot be null");
			throw new RuntimeException("entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			log.warn("unknown user");
			throw new RuntimeException("unknown user");
		}
	}
	
	public List<TodoEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	
}
