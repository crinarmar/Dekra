package com.dekra.service.use_case;

import org.springframework.stereotype.Service;

import com.dekra.service.Product;
import com.dekra.service.repository.ProductRepository;

@Service
public class CreateProductImpl implements CreateProduct {

	private final ProductRepository productRepository;

	public CreateProductImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void createProduct(Product product) {
		this.productRepository.save(product);
	}

}
