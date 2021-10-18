package com.example.study.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor //모든 필든값을 받는 생성자를 만들어주는 어노테이션
@NoArgsConstructor // 필드값이 없는 생성자를 만들어주는 어노테이션
@Entity
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder //생성자 체이닝
@Accessors(chain = true) //setter 체인이닝
public class Users {
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "Users_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "Users_SEQUENCE_GENERATOR", sequenceName = "Users_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	//LAZY:지연로딩, 1:N 일때 사용
	//EAGER:즉시로딩, 1:1 일때 사용
	// 1:N
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<OrderGroup> orderGroupList;
	
}
