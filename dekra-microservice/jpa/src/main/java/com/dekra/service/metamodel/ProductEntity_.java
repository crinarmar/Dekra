package com.dekra.service.metamodel;

import com.dekra.service.ProductEntity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductEntity.class)
public class ProductEntity_ {

	public static volatile SingularAttribute<ProductEntity, String> name;
	public static volatile SingularAttribute<ProductEntity, String> description;
	public static volatile SingularAttribute<ProductEntity, Double> price;

}