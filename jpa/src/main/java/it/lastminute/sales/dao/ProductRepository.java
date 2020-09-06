package it.lastminute.sales.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import it.lastminute.sales.dao.entity.Product;

public interface ProductRepository extends CrudRepository<Product, UUID> {

}
