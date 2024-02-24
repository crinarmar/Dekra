package com.dekra.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dekra.service.ProductEntity;

public class ProductByNameLike {

	private final String name;

	public ProductByNameLike(String name) {
		this.name = name;
	}

	public Specification<ProductEntity> getSpecification() {

		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
				"%" + this.name.trim().toUpperCase() + "%");
	}

}
