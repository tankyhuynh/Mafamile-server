package com.mafami.Mafami.Repository.AMI_DESIGN;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_PostEntity;

@Repository
public interface AMIDESIGN_PostRepo extends MongoRepository<AMIDESIGN_PostEntity, String>{

	List<AMIDESIGN_PostEntity> findAllByAuthor(UserEntity author);
	
}
