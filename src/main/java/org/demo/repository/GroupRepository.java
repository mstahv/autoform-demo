package org.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.demo.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
