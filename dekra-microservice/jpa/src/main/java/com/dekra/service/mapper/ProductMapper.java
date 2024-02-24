package com.dekra.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.dekra.service.Product;
import com.dekra.service.ProductEntity;

/**
 * Mapper from entity PriceEntity to domain Price
 * @author fjcastano
 */
@Component
public class ProductMapper {

	public Collection<Product> mapTo(Collection<ProductEntity> sourceCollection) {

		if (Objects.isNull(sourceCollection) || sourceCollection.isEmpty()) {
			return Collections.emptyList();
		}

		Collection<Product> result = new ArrayList<>();
		sourceCollection.forEach(priceEntity -> result.add(this.mapTo(priceEntity)));
		return result;
	}

	public Product mapTo(ProductEntity source) {
		return Product.maker().id(source.getId()).name(source.getName()).description(source.getDescription()).price(source.getPrice()).build();
	}

	public ProductEntity mapReverse(Product source) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(source.getId());
		productEntity.setName(source.getName());
		productEntity.setDescription(source.getDescription());
		productEntity.setPrice(source.getPrice());
		return productEntity;
	}
}
