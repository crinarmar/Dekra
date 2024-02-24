package com.dekra.service.use_case;

import java.util.Collection;

import com.dekra.service.Product;

public interface SearchProduct {

	Product searchProductById(Long id);

	Collection<Product> searchAllProduct();

	Collection<Product> searchProductByFilter(String name, String description, Double price);

}
