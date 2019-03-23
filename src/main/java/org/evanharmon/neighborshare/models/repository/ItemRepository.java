package org.evanharmon.neighborshare.models.repository;

import org.evanharmon.neighborshare.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, Integer> {


}
