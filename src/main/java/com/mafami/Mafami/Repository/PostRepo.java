package com.mafami.Mafami.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.PostEntity;

@Repository
public interface PostRepo extends MongoRepository<PostEntity, String> {

	PostEntity findOneById(String id);
	PostEntity findOneByUsername(String username);
	
}
