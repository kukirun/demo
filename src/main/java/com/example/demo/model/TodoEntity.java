package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")//generator로 어떤 방식으로 id 생성할지 지정
	@GenericGenerator(name = "system-uuid", strategy = "uuid")//hibernate가 제공하는 기본이 아닌 나만의 generator 사용하고 싶을때 이용
	private String id;
	private String userId;
	private String title;
	private boolean done;
	
}
