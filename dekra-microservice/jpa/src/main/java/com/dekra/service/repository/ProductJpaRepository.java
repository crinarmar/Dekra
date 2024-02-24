package com.dekra.service.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.dekra.service.Product;
import com.dekra.service.ProductEntity;
import com.dekra.service.criteria.ProductCriteria;
import com.dekra.service.mapper.ProductMapper;
import com.dekra.service.repository.base.BaseJpaRepository;
import com.dekra.service.specification.ProductByDescriptionLike;
import com.dekra.service.specification.ProductByNameLike;
import com.dekra.service.specification.ProductByPriceEqual;

@Repository
public class ProductJpaRepository implements ProductRepository {

	private final BaseJpaRepository baseRepository;
	private final ProductMapper mapper;

	public ProductJpaRepository(BaseJpaRepository repository, ProductMapper mapper) {
		this.baseRepository = repository;
		this.mapper = mapper;
	}

	@Override
	public Collection<Product> findAll() {
		return this.mapper.mapTo(this.baseRepository.findAll());
	}

	@Override
	public Optional<Product> findById(Long id) {

		Optional<ProductEntity> optionalEntity = this.baseRepository.findById(id);

		if (optionalEntity.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(this.mapper.mapTo(optionalEntity.get()));
	}

	@Override
	public void save(Product source) {
		this.baseRepository.save(this.mapper.mapReverse(source));
	}

	@Override
	public void delete(Product source) {
		this.baseRepository.delete(this.mapper.mapReverse(source));

	}

	@Override
	public void update(Product source) {
		this.baseRepository.save(this.mapper.mapReverse(source));
	}

	@Override
	public Collection<Product> findBy(ProductCriteria criteria) {
		return this.mapper.mapTo(this.baseRepository.findAll(this.getCriteriaSpecification(criteria)));
	}

	private Specification<ProductEntity> getCriteriaSpecification(ProductCriteria criteria) {

		Specification<ProductEntity> specification = Specification.where(null);

		if (Objects.nonNull(criteria.getName())) {
			specification = specification.and(new ProductByNameLike(criteria.getName()).getSpecification());
		}

		if (Objects.nonNull(criteria.getDescription())) {
			specification = specification.and(new ProductByDescriptionLike(criteria.getDescription()).getSpecification());
		}

		if (Objects.nonNull(criteria.getPrice())) {
			specification = specification.and(new ProductByPriceEqual(criteria.getPrice()));
		}

		return specification;
	}
}
