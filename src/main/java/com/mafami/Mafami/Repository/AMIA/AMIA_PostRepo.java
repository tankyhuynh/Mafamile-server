package com.mafami.Mafami.Repository.AMIA;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_PostEntity;


@Repository
public interface AMIA_PostRepo extends MongoRepository<AMIA_PostEntity, String> {

	AMIA_PostEntity findOneById(String id);
	AMIA_PostEntity findOneByAuthor(String author);
	
}
