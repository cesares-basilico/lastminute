package it.lastminute.sales.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.lastminute.sales.dao.entity.ProductTypeLkp;

public interface ProductTypeRepository extends CrudRepository<ProductTypeLkp, Integer> {

    Optional<ProductTypeLkp> findById(final String id);

}
