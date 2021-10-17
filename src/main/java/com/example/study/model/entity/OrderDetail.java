package com.example.study.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"userr", "item"})
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@Column(name = "order_at")
	private LocalDateTime orderAt;
	
	//@Column(name = "user_id")
	@ManyToOne // N:!
	private Userr userr ; // 키값인 id(Userr)를 알아서 찾아감 
	
	//@Column(name = "item_id")
	@ManyToOne
	private Item item; // 키값인 id(Item)를 알아서 찾아감
}
