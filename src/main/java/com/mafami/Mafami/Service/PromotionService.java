package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Entity.PromotionEntity;
import com.mafami.Mafami.Repository.PromotionRepo;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepo promotionRepo;

	public PromotionEntity findOneById(String id) {
		return promotionRepo.findOneById(id);
	}

	public List<PromotionEntity> getAll() {
		return promotionRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}
	
	public List<PromotionEntity> getAllBySite(String site) {
		return promotionRepo.findAllBySite(site);
	}

	public PromotionEntity save(PromotionEntity entity) {
		return promotionRepo.save(entity);
	}

	public void delete(String id) {
		promotionRepo.delete(promotionRepo.findOneById(id));
	}

	public void deleteAllBySite(String site) {
		List<PromotionEntity> listContacts = promotionRepo.findAllBySite(site);
		promotionRepo.deleteAll(listContacts);
	}
	
	public void deleteAll() {
		promotionRepo.deleteAll();
	}

}
