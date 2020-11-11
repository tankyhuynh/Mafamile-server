package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CategoryEntity;

@Repository
public interface MAFAMILE_CategoryRepo extends MongoRepository<MAFAMILE_CategoryEntity, String> {

	MAFAMILE_CategoryEntity findOneById(String id);
	MAFAMILE_CategoryEntity findOneBySlug(String slug);
	
}
