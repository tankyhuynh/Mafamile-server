package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_PostEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_PostRepo;

@Service
public class MFAMILE_PostService {

	@Autowired
	private MAFAMILE_PostRepo mAFAMILE_PostRepo;

	public MAFAMILE_PostEntity findOneById(String idPost) {
		return mAFAMILE_PostRepo.findOneById(idPost);
	}

	public MAFAMILE_PostEntity findOneByAuthor(String author) {
		return mAFAMILE_PostRepo.findOneByAuthor(author);
	}

	public List<MAFAMILE_PostEntity> getAll() {
		return mAFAMILE_PostRepo.findAll();
	}

	public MAFAMILE_PostEntity save(MAFAMILE_PostEntity entity) {
		return mAFAMILE_PostRepo.save(entity);
	}

	public void delete(String id) {
		mAFAMILE_PostRepo.delete(mAFAMILE_PostRepo.findOneById(id));
	}
	
	public void deleteAll() {
		mAFAMILE_PostRepo.deleteAll();
	}

}
