package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_PostEntity;
import com.mafami.Mafami.Repository.AMIA.AMIA_PostRepo;

@Service
public class AMIA_PostService {

	@Autowired
	private AMIA_PostRepo amia_PostRepo;

	public AMIA_PostEntity findOneById(String idPost) {
		return amia_PostRepo.findOneById(idPost);
	}

	public AMIA_PostEntity findOneByAuthor(String author) {
		return amia_PostRepo.findOneByAuthor(author);
	}

	public List<AMIA_PostEntity> getAll() {
		return amia_PostRepo.findAll();
	}

	public AMIA_PostEntity save(AMIA_PostEntity entity) {
		return amia_PostRepo.save(entity);
	}

	public void delete(String id) {
		amia_PostRepo.delete(amia_PostRepo.findOneById(id));
	}

}
