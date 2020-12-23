package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Entity.RecruitmentEntity;
import com.mafami.Mafami.Repository.RecruitmentRepo;

@Service
public class RecruitmentService {

	@Autowired
	private RecruitmentRepo recruitmentRepo;
	
	public RecruitmentEntity findOneById(String id) {
		return recruitmentRepo.findOneById(id);
	}

	public List<RecruitmentEntity> getAll() {
		return recruitmentRepo.findAll();
	}
	
	public List<RecruitmentEntity> getAllBySite(String site) {
		return recruitmentRepo.findAllBySite(site);
	}

	public RecruitmentEntity save(RecruitmentEntity entity) {
		return recruitmentRepo.save(entity);
	}

	public void delete(String id) {
		recruitmentRepo.delete(recruitmentRepo.findOneById(id));
	}
	
	public void deleteAllBySite(String site) {
		List<RecruitmentEntity> listContacts = recruitmentRepo.findAllBySite(site);
		recruitmentRepo.deleteAll(listContacts);
	}
	
	public void deleteAll() {
		recruitmentRepo.deleteAll();
	}
	
	
	
}
