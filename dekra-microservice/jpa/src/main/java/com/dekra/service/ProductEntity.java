package com.dekra.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@Column(name = "PRODUCT_ID", length = 9, nullable = false, unique = true)
	private Long id;

	@Column(name = "PRODUCT_NAME", length = 30, nullable = false, unique = false)
	private String name;

	@Column(name = "PRODUCT_DESCRIPTION", length = 200, nullable = false, unique = false)
	private String description;

	@Column(name = "PRICE", nullable = false, unique = false)
	private Double price;

}
