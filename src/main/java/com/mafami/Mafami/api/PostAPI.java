package com.mafami.Mafami.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Service.PostService;
import com.mafami.Mafami.Utils.FileUtils;

@RestController
@RequestMapping("/api//post")
public class PostAPI {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping
	public ResponseEntity<List<PostEntity>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/{site}")
	public ResponseEntity<List<PostEntity>> getAllBySite(@PathVariable("site") String site) {
		return ResponseEntity.ok(postService.findAllBySite(site));
	}

	
	@GetMapping("/{site}/{id}")
	public ResponseEntity<PostEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(postService.findOneById(id));
	}
	
	
	@PostMapping("/{site}")
	public ResponseEntity<PostEntity> saveOne(@PathVariable("site") String site, @RequestBody PostEntity entity) {
		entity.setSite(site);
		if (entity.getThumbnail() != null) {
			String URL = fileUtils.decoder(entity.getThumbnail(), "outputFile");
			entity.setThumbnail(URL);
		}
		return ResponseEntity.ok(postService.save(entity));
	}
	
	@PutMapping("/{site}/{id}")
	public PostEntity saveOneById(@PathVariable("site") String site, @PathVariable String id, @RequestBody PostEntity postEntity) {
		postEntity.setId(id);
		postEntity.setSite(site);
		
		return postService.save(postEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		postService.delete(id);
	}
	
	@DeleteMapping("/all/{site}")
	public void deleteAllBySite(@PathVariable("site") String site) {
		postService.deleteAll();
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		postService.deleteAll();
	}
	
	
	
}
