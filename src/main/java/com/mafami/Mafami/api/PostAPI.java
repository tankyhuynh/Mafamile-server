package com.mafami.Mafami.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Repository.PostRepo;
import com.mafami.Mafami.Service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostAPI {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<PostEntity> getAll() {
		return postService.getAll();
	}
	
	@GetMapping("/{id}")
	public PostEntity getOneById(@PathVariable String id) {
		return postService.getOneById(id);
	}
	
	@PostMapping
	public PostEntity save(@RequestBody PostEntity entity) {
		return postService.save(entity);
	}
	
	@PutMapping("/{id}")
	public PostEntity putOneById(@PathVariable String id, @RequestBody PostEntity postEntity) {
		postEntity.setId(id);
		
		return postService.save(postEntity);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		 postService.delete(id);
	}
	
	
	
	
	
	
	
}
