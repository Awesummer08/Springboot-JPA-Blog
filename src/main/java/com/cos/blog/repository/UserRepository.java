package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}


// security 적용 전 방법
// 로그인을 위한 어떤 함수 만들자
// JPA Naming 전략
// SELECT * FROM user WHERE username = ? AND password = ?2;
//User findByUsernameAndPassword(String username, String password);

//@Query(Value="SELECT * FROM user WHERE username = ? AND password = ?2;", naviveQuery=true)
//User login(String username, String password);