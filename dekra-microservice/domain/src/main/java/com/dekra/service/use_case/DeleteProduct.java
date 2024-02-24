package com.dekra.service.use_case;

import com.dekra.service.Product;

public interface DeleteProduct {

	void deleteProduct(Long id);

	void deleteProduct(Product product);

}
