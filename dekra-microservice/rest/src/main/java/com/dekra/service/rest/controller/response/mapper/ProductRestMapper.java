package com.dekra.service.rest.controller.response.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.dekra.service.Product;
import com.dekra.service.rest.interfaces.product.dto.ProductRest;

/**
 * Mapeador de objetos de dominio a objetos Rest
 * @author fjcastano
 */
@Component
public class ProductRestMapper {

	public ProductRest mapTo(Product source) {
		Objects.requireNonNull(source);
		ProductRest productRest = new ProductRest(source.getId(), source.getName(), source.getDescription(), source.getPrice());
		productRest.setTax(source.getTax());
		productRest.setTotalPrice(source.getTotalPrice());
		return productRest;
	}

	public List<ProductRest> mapTo(Collection<Product> source) {
		Objects.requireNonNull(source);
		List<ProductRest> destinyCollection = new ArrayList<>();
		source.forEach(sourcePrice -> destinyCollection.add(this.mapTo(sourcePrice)));
		return destinyCollection;
	}

	public Product mapReverse(ProductRest source) {
		Objects.requireNonNull(source);
		return Product.maker().id(source.getId()).name(source.getName()).description(source.getDescription()).price(source.getPrice()).build();
	}

}
