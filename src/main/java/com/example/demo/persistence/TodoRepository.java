package com.example.demo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{ // JpaRepository<T, ID> T=���̺� ���ε� entity, id=�⺻Ű�� Ÿ��
	List<TodoEntity> findByUserId(String userId); // �̷��� ���� SELECT * FROM TodoRepository WHERE userId={userId} �� ���� ȿ��
	//?1�� �޼��� �Ű������� ���� ��ġ
//	@Query(value = "select * from Todo t where t.userId = ?1", nativeQuery = true)
//	List<TodoEntity> findByUserId(String userId);
	@Query(value = "select * from Todo t where t.Id = ?1", nativeQuery = true)
	Optional<TodoEntity> test(String userId);
}
