package com.mafami.Mafami.Repository.AMIA;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;
import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;

@Repository
public interface AMIA_ProductRepo extends MongoRepository<AMIA_ProductEntity, String> {

	List<AMIA_ProductEntity> findAllByCategory(AMIA_CategoryEntity categoryCode);

	AMIA_ProductEntity findOneByName(String name);

}
