package com.mafami.Mafami.Service.AMI_DESIGN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_PostEntity;
import com.mafami.Mafami.Repository.AMI_DESIGN.AMIDESIGN_PostRepo;

@Service
public class PostService {

	@Autowired
	private AMIDESIGN_PostRepo aMIDESIGN_PostRepo;
	
	public List<AMIDESIGN_PostEntity> findAll() {
		return aMIDESIGN_PostRepo.findAll();
	}
	
	public AMIDESIGN_PostEntity findOneById(String id) {
		return aMIDESIGN_PostRepo.findById(id).orElse(new AMIDESIGN_PostEntity());
	}
	
	public AMIDESIGN_PostEntity save(AMIDESIGN_PostEntity aMIDESIGN_PostEntity) {
		return aMIDESIGN_PostRepo.save(aMIDESIGN_PostEntity);
	}
	
	public AMIDESIGN_PostEntity updateById(String id, AMIDESIGN_PostEntity aMIDESIGN_PostEntity) {
		aMIDESIGN_PostEntity.setId(id);
		return aMIDESIGN_PostRepo.save(aMIDESIGN_PostEntity);
	}
	
	public void delete(String id) {
		aMIDESIGN_PostRepo.deleteById(id);
	}
	
	
	
}
