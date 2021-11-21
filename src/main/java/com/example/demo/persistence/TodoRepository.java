package com.example.demo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{ // JpaRepository<T, ID> T=테이블에 매핑될 entity, id=기본키의 타입
	List<TodoEntity> findByUserId(String userId); // 이렇게 쓰면 SELECT * FROM TodoRepository WHERE userId={userId} 와 같은 효과
	//?1은 메서드 매개변수의 순서 위치
//	@Query(value = "select * from Todo t where t.userId = ?1", nativeQuery = true)
//	List<TodoEntity> findByUserId(String userId);
	@Query(value = "select * from Todo t where t.Id = ?1", nativeQuery = true)
	Optional<TodoEntity> test(String userId);
}
