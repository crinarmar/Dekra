package com.dekra.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dekra.service.ProductEntity;
import com.dekra.service.metamodel.ProductEntity_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductByPriceEqual implements Specification<ProductEntity> {

	private static final long serialVersionUID = 1L;
	private final Double price;

	public ProductByPriceEqual(Double price) {
		this.price = price;
	}

	@Override
	public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.equal(root.get(ProductEntity_.price), this.price);
	}

}
