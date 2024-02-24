package com.dekra.service.rest.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.dekra.service.rest.controller.response.mapper.ProductRestMapper;
import com.dekra.service.rest.interfaces.product.ProductosApi;
import com.dekra.service.rest.interfaces.product.dto.ProductRest;
import com.dekra.service.use_case.CreateProduct;
import com.dekra.service.use_case.DeleteProduct;
import com.dekra.service.use_case.SearchProduct;
import com.dekra.service.use_case.UpdateProduct;

@RestController
public class ProductRestController implements ProductosApi {

	private final SearchProduct searchProduct;
	private final CreateProduct createProduct;
	private final DeleteProduct deleteProduct;
	private final UpdateProduct updateProduct;
	private final ProductRestMapper mapper;

	public ProductRestController(SearchProduct searchProduct, ProductRestMapper mapper, CreateProduct createProduct, DeleteProduct deleteProduct,
			UpdateProduct updateProduct) {

		this.searchProduct = searchProduct;
		this.createProduct = createProduct;
		this.deleteProduct = deleteProduct;
		this.updateProduct = updateProduct;

		this.mapper = mapper;
	}

	@Override
	public ResponseEntity<List<ProductRest>> listProducts() {
		return ResponseEntity.ok(this.mapper.mapTo(this.searchProduct.searchAllProduct()));
	}

	@Override
	public ResponseEntity<ProductRest> findProductById(Long id) {
		Objects.nonNull(id);
		return ResponseEntity.ok(this.mapper.mapTo(this.searchProduct.searchProductById(id)));
	}

	@Override
	public ResponseEntity<Void> createProduct(ProductRest productRest) {
		Objects.nonNull(productRest);
		this.createProduct.createProduct(this.mapper.mapReverse(productRest));
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
	}

	@Override
	public ResponseEntity<Void> deleteProductById(Long id) {
		Objects.nonNull(id);
		this.deleteProduct.deleteProduct(id);
		return ResponseEntity.status(HttpStatusCode.valueOf(202)).build();
	}

	@Override
	public ResponseEntity<Void> updateProduct(Long id, ProductRest productRest) {
		Objects.nonNull(id);
		Objects.nonNull(productRest);
		this.updateProduct.updateProduct(this.mapper.mapReverse(productRest));
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
	}

	@Override
	public ResponseEntity<List<ProductRest>> listProductsByFilter(String name, String description, Double price) {
		return ResponseEntity.ok(this.mapper.mapTo(this.searchProduct.searchProductByFilter(name, description, price)));
	}

}
