package com.mafami.Mafami.Repository.MAFAMILE;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;

@Repository
public interface MAFAMILE_MenuRepo extends MongoRepository<MAFAMILE_MenuEntity, String> {

	
	MAFAMILE_MenuEntity findOneById(String id);
	List<MAFAMILE_MenuEntity> findAllByCategory(String category);
	MAFAMILE_MenuEntity findOneByName(String name);
	
}
