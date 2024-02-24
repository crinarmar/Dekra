package com.dekra.service.use_case;

import org.springframework.stereotype.Service;

import com.dekra.service.Product;
import com.dekra.service.repository.ProductRepository;

@Service
public class DeleteProductImpl implements DeleteProduct {

	private final SearchProduct searchProduct;
	private final ProductRepository productRepository;

	// @Inject
	public DeleteProductImpl(SearchProduct searchProduct, ProductRepository productRepository) {
		this.searchProduct = searchProduct;
		this.productRepository = productRepository;
	}

	@Override
	public void deleteProduct(Long id) {
		this.deleteProduct(this.searchProduct.searchProductById(id));

	}

	@Override
	public void deleteProduct(Product product) {
		this.productRepository.delete(product);
	}

}
