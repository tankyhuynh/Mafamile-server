package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Repository.PostRepo;

@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;
	
	public List<PostEntity> findAll() {
		return postRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}
	
	public List<PostEntity> findAllByAuthor(UserEntity author) {
		return postRepo.findAllByAuthor(author);
	}
	
	public List<PostEntity> findAllBySite(String site) {
		return postRepo.findAllBySite(site);
	}
	
	public PostEntity findOneById(String id) {
		return postRepo.findById(id).orElse(new PostEntity());
	}
	
	public PostEntity save(PostEntity aMIDESIGN_PostEntity) {
		return postRepo.save(aMIDESIGN_PostEntity);
	}
	
	public PostEntity updateById(String id, PostEntity aMIDESIGN_PostEntity) {
		aMIDESIGN_PostEntity.setId(id);
		return postRepo.save(aMIDESIGN_PostEntity);
	}
	
	public void delete(String id) {
		postRepo.deleteById(id);
	}
	
	public void deleteAllBySite(String site) {
		List<PostEntity> listPosts = postRepo.findAllBySite(site);
		postRepo.deleteAll(listPosts);
	}
	
	public void deleteAll() {
		postRepo.deleteAll();
	}
	
	
	
}
