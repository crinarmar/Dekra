package com.dekra.service.repository;

import java.util.Collection;
import java.util.Optional;

import com.dekra.service.Product;
import com.dekra.service.criteria.ProductCriteria;

public interface ProductRepository {

	Collection<Product> findBy(ProductCriteria criteria);

	Collection<Product> findAll();

	Optional<Product> findById(Long id);

	void save(Product product);

	void delete(Product product);

	void update(Product product);

}
