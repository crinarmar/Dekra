package com.dekra.service;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "maker")
@Getter
public class Product {

	private Long id;
	private String name;
	private String description;
	private Double price;

	private Double tax;
	private Double totalPrice;

	public void applyTax(Double tax) {
		this.tax = tax;
		this.totalPrice = this.price + ((tax / 100.0) * this.price);
	}

}
