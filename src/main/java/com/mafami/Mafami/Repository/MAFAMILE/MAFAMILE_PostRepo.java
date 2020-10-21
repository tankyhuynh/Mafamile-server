package com.mafami.Mafami.Repository.MAFAMILE;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_PostEntity;

@Repository
public interface MAFAMILE_PostRepo extends MongoRepository<MAFAMILE_PostEntity, String> {

	MAFAMILE_PostEntity findOneById(String id);
	MAFAMILE_PostEntity findOneByAuthor(String author);
	
}
