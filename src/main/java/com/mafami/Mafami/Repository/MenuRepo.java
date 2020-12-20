package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.MenuEntity;

@Repository
public interface MenuRepo extends MongoRepository<MenuEntity, String> {

	List<MenuEntity> findAllByCategory(CategoryEntity categoryCode);
	List<MenuEntity> findAllBySite(String site);

	MenuEntity findOneByName(String name);

}
