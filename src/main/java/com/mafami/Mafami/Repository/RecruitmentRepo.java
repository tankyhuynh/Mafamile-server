package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Entity.RecruitmentEntity;

@Repository
public interface RecruitmentRepo extends MongoRepository<RecruitmentEntity, String> {

	RecruitmentEntity findOneById(String id);
	RecruitmentEntity findOneByTitle(String title);
	List<RecruitmentEntity> findAllBySite(String site);
	
}
