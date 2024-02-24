package com.dekra.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dekra.service.ProductEntity;
import com.dekra.service.metamodel.ProductEntity_;

public class ProductByDescriptionLike {

	private final String description;

	public ProductByDescriptionLike(String description) {
		this.description = description;
	}

	public Specification<ProductEntity> getSpecification() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ProductEntity_.description)),
				"%" + this.description.trim().toUpperCase() + "%");
	}

}
