package com.mafami.Mafami.Repository.AMI_DESIGN;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_GalleryEntity;

@Repository
public interface AMIDESIGN_GalleryRepo extends MongoRepository<AMIDESIGN_GalleryEntity, String>{

	
	
}
