package com.dekra.service.use_case;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dekra.service.Product;
import com.dekra.service.criteria.ProductCriteria;
import com.dekra.service.exception.ProductNotFoundException;
import com.dekra.service.repository.ProductRepository;
import com.dekra.service.use_case.util.CalculadorDeImpuestos;

@Service
public class SearchProductImpl implements SearchProduct {

	private final ProductRepository productRepository;
	private final CalculadorDeImpuestos calculadorDeImpuestos;

	public SearchProductImpl(ProductRepository productRepository, CalculadorDeImpuestos calculadorDeImpuestos) {
		this.productRepository = productRepository;
		this.calculadorDeImpuestos = calculadorDeImpuestos;
	}

	@Override
	public Product searchProductById(Long id) {

		Optional<Product> optionalProduct = this.productRepository.findById(id);

		if (optionalProduct.isEmpty()) {
			throw new ProductNotFoundException();
		}

		Product product = optionalProduct.get();
		this.calculadorDeImpuestos.calcularImpuesto(product);
		return product;
	}

	@Override
	public Collection<Product> searchAllProduct() {
		return this.productRepository.findAll().stream().parallel().peek(this.calculadorDeImpuestos::calcularImpuesto).collect(Collectors.toList());
	}

	@Override
	public Collection<Product> searchProductByFilter(String name, String description, Double price) {
		return this.productRepository.findBy(new ProductCriteria(name, description, price)).stream().parallel()
				.peek(this.calculadorDeImpuestos::calcularImpuesto).collect(Collectors.toList());
	}

}
