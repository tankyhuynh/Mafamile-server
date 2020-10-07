package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.PostEntity;
import com.mafami.Mafami.Repository.MAFAMILE.PostRepo;

@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;
	
	public PostEntity findOneById(String idPost) {	return postRepo.findOneById(idPost); 	}
	
	public PostEntity findOneByUsername(String username) {	return postRepo.findOneByUsername(username); 	}

	public List<PostEntity> getAll() {		return postRepo.findAll();		}

	public PostEntity save(PostEntity entity) {		return postRepo.save(entity);		}
	
	public void delete(String id) {		postRepo.delete(postRepo.findOneById(id));		}
	
	
}
