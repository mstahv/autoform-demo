package org.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.demo.entity.Technology;

/**
 * @author Boniface Chacha
 */
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

	List<Technology> findAllByParent(Technology parent);

	List<Technology> findAllByParentIsNull();

}
