package com.example.study.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) // 밑에 @CreatedBy, @LastModifiedBy를 사용하기 위해
@Builder
@Accessors(chain = true)
public class AdminUsers {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "AdminUsers_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "AdminUsers_SEQUENCE_GENERATOR", sequenceName = "AdminUsers_SEQUENCE", initialValue = 1, allocationSize = 1)
	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String role;
	
	private LocalDateTime lastLoginAt;
	
	private LocalDateTime passwordUpdatedAt;
	
	private int loginFailCount;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	@CreatedDate // 자동으로 오늘 날짜가 들어간다.
	private LocalDateTime createdAt;
	
	@CreatedBy //LoginUserAuditorAware 클래스의 getCurrentAuditor()값이 자동으로 들어간다.
	private String createdBy;
	
	@LastModifiedDate // 자동으로 수정날짜가 들어간다.
	private LocalDateTime updatedAt;
	
	@LastModifiedBy //LoginUserAuditorAware 클래스의 getCurrentAuditor()값이 자동으로 들어간다.
	private String updatedBy;
}
