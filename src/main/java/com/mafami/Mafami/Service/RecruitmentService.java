package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.RecruitmentEntity;
import com.mafami.Mafami.Repository.RecruitmentRepo;

@Service
public class RecruitmentService {

	@Autowired
	private RecruitmentRepo recruitmentRepo;

	public RecruitmentEntity findOneById(String id) {
		return recruitmentRepo.findOneById(id);
	}

	public List<RecruitmentEntity> findAll() {
		return recruitmentRepo.findAll();
	}

	public List<RecruitmentEntity> findAllBySite(String site) {
		return recruitmentRepo.findAllBySite(site);
	}

	public List<RecruitmentEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return recruitmentRepo.findAll(pageable).getContent();
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
