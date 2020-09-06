package it.lastminute.sales.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import it.lastminute.sales.dao.entity.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, UUID> {

}
