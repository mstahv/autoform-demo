package org.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.demo.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByNameContainingIgnoreCase(String name);

}
