package org.evanharmon.neighborshare.models.repository;

import org.evanharmon.neighborshare.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
