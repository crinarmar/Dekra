package com.dekra.service.use_case.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.dekra.service.Product;

@Component
@ConditionalOnProperty(value = "config.tax", havingValue = "IVA")
public class CalculadorDeImpuestosIVAImpl implements CalculadorDeImpuestos {

	private static final Double TAX = 21.0;

	@Override
	public void calcularImpuesto(Product product) {
		product.applyTax(TAX);
	}

}
