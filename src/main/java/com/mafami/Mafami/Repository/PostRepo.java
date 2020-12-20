package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Entity.UserEntity;

@Repository
public interface PostRepo extends MongoRepository<PostEntity, String>{

	List<PostEntity> findAllByAuthor(UserEntity author);
	List<PostEntity> findAllBySite(String site);
	
}
