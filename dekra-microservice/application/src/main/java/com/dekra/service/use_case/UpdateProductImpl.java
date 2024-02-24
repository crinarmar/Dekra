package com.dekra.service.use_case;

import org.springframework.stereotype.Service;

import com.dekra.service.Product;
import com.dekra.service.repository.ProductRepository;

@Service
public class UpdateProductImpl implements UpdateProduct {

	private final ProductRepository productRepository;
	private final SearchProduct searchProduct;

	// @Inject
	public UpdateProductImpl(ProductRepository productRepository, SearchProduct searchProduct) {
		this.productRepository = productRepository;
		this.searchProduct = searchProduct;
	}

	@Override
	public void updateProduct(Product product) {
		this.searchProduct.searchProductById(product.getId());
		this.productRepository.save(product);
	}
}
