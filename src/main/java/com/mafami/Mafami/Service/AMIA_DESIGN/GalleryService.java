package com.mafami.Mafami.Service.AMIA_DESIGN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMI_DESIGN.GalleryEntity;
import com.mafami.Mafami.Repository.AMI_DESIGN.GalleryRepo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepo galleryRepo;
	
	public List<GalleryEntity> findAll() {
		return galleryRepo.findAll();
	}
	
	public GalleryEntity findOneById(String id) {
		return galleryRepo.findById(id).orElse(new GalleryEntity());
	}
	
	public GalleryEntity save(GalleryEntity galleryEntity) {
		return galleryRepo.save(galleryEntity);
	}
	
	public GalleryEntity updateById(String id, GalleryEntity galleryEntity) {
		galleryEntity.setId(id);
		return galleryRepo.save(galleryEntity);
	}
	
	public void delete(String id) {
		galleryRepo.deleteById(id);
	}
	
	
	
}
