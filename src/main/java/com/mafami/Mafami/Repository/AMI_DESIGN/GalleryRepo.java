package com.mafami.Mafami.Repository.AMI_DESIGN;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMI_DESIGN.GalleryEntity;

@Repository
public interface GalleryRepo extends MongoRepository<GalleryEntity, String>{

	
	
}
