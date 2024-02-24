package com.dekra.service.use_case;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dekra.service.Product;
import com.dekra.service.exception.ProductNotFoundException;
import com.dekra.service.repository.ProductRepository;
import com.dekra.service.use_case.util.CalculadorDeImpuestos;

@ExtendWith(MockitoExtension.class)
class SearchProductImplTest {

	@InjectMocks
	private SearchProductImpl searchProductImpl;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private CalculadorDeImpuestos calculadorDeImpuestos;

	@Test
	void searchAllProduct() {
		when(this.productRepository.findAll()).thenReturn(Collections.emptyList());
		assertEquals(0, this.searchProductImpl.searchAllProduct().size());
	}

	@Test
	void searchById_productNotFound() {
		when(this.productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		assertThrows(ProductNotFoundException.class, () -> this.searchProductImpl.searchProductById(1L));
	}

	@Test
	void searchById() {
		when(this.productRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(Product.maker().id(1L).name("name").description("description").price(1.0).build()));
		assertEquals(1L, this.searchProductImpl.searchProductById(1L).getId());
	}

	@Test
	void searchProductByFilter() {
		Collection<Product> result = new ArrayList<>();
		result.add(Product.maker().id(1L).name("name").description("description").price(1.0).build());
		when(this.productRepository.findBy(Mockito.any())).thenReturn(result);

		assertEquals(1L, this.searchProductImpl.searchProductByFilter("name", "description", null).size());
	}

}
