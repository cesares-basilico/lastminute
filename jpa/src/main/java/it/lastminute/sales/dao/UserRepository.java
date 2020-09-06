package it.lastminute.sales.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import it.lastminute.sales.dao.entity.User;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsernameAndPassword(final String username, final String password);

}
