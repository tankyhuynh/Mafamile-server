package com.mafami.Mafami.Service.AMI_DESIGN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_GalleryEntity;
import com.mafami.Mafami.Repository.AMI_DESIGN.AMIDESIGN_GalleryRepo;

@Service
public class GalleryService {

	@Autowired
	private AMIDESIGN_GalleryRepo aMIDESIGN_GalleryRepo;
	
	public List<AMIDESIGN_GalleryEntity> findAll() {
		return aMIDESIGN_GalleryRepo.findAll();
	}
	
	public AMIDESIGN_GalleryEntity findOneById(String id) {
		return aMIDESIGN_GalleryRepo.findById(id).orElse(new AMIDESIGN_GalleryEntity());
	}
	
	public AMIDESIGN_GalleryEntity save(AMIDESIGN_GalleryEntity aMIDESIGN_GalleryEntity) {
		return aMIDESIGN_GalleryRepo.save(aMIDESIGN_GalleryEntity);
	}
	
	public AMIDESIGN_GalleryEntity updateById(String id, AMIDESIGN_GalleryEntity aMIDESIGN_GalleryEntity) {
		aMIDESIGN_GalleryEntity.setId(id);
		return aMIDESIGN_GalleryRepo.save(aMIDESIGN_GalleryEntity);
	}
	
	public void delete(String id) {
		aMIDESIGN_GalleryRepo.deleteById(id);
	}
	
	
	
}
