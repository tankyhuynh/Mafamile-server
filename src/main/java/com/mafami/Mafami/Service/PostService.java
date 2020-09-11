package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Repository.PostRepo;

@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;
	
	public PostEntity getOneById(String id) {	return postRepo.findOneById(id); 	}

	public List<PostEntity> getAll() {		return postRepo.findAll();		}

	public PostEntity save(PostEntity entity) {		return postRepo.save(entity);		}
	
	public void delete(String id) {		postRepo.delete(postRepo.findOneById(id));		}
	
	
}
