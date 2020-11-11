package com.mafami.Mafami.Repository.AMIA;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;

@Repository
public interface AMIA_CategoryRepo extends MongoRepository<AMIA_CategoryEntity, String> {

	AMIA_CategoryEntity findOneById(String id);
	AMIA_CategoryEntity findOneBySlug(String slug);

}
