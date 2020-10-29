package com.mafami.Mafami.Repository.AMIA;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;
import com.mafami.Mafami.Entity.AMIA.AMIA_MenuEntity;

@Repository
public interface AMIA_MenuRepo extends MongoRepository<AMIA_MenuEntity, String> {

	List<AMIA_MenuEntity> findAllByCategory(AMIA_CategoryEntity categoryCode);

	AMIA_MenuEntity findOneByName(String name);

}
