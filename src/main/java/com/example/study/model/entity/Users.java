package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 필든값을 받는 생성자를 만들어주는 어노테이션
@NoArgsConstructor // 필드값이 없는 생성자를 만들어주는 어노테이션
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "Users_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "Users_SEQUENCE_GENERATOR", sequenceName = "Users_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String email;
	
	//@Column(name = "phone_number")
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	//@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	//@Column(name = "created_by")
	private String createdBy;
	
	//@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	//@Column(name = "updated_by")
	private String updatedBy;
	
	//LAZY:지연로딩, 1:N 일때 사용
	//EAGER:즉시로딩, 1:1 일때 사용
	// 1:N
//	@OneToMany(fetch= FetchType.LAZY, mappedBy = "users")
//	private List<OrderDetail> orderDetail;
}
