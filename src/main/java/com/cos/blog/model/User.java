package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 MySQL에 테이블이 생성된다.
//@DynamicInsert insert할때 null인 필드를 제외해준다.
public class User { 
	  
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique=true)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;   
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다.
	
	private String oauth;
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;	
	
	
	
}
