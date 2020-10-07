package com.mafami.Mafami.api.MAFAMILE;

import java.util.ArrayList;
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

import com.mafami.Mafami.Entity.MAFAMILE.PostEntity;
import com.mafami.Mafami.Service.MAFAMILE.PostService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/post")
public class PostAPI {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public List<PostEntity> getAll() {
		return postService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(postService.findOneById(id));
	}
	
	@PostMapping
	public ResponseEntity<PostEntity> saveOne(@RequestBody PostEntity entity) {
		List<String> listImages = new ArrayList<>();
		for (String item : entity.getImages()) {
			String URL = fileUtils.decoder(item, "outputFile");
			listImages.add(URL);
		}
		entity.setImages(listImages);
		
		return ResponseEntity.ok(postService.save(entity));
	}
	
	@PutMapping("/{id}")
	public PostEntity saveOneById(@PathVariable String id, @RequestBody PostEntity postEntity) {
		postEntity.setId(id);
		
		return postService.save(postEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		 postService.delete(id);
	}
	
	
	
	
	
	
	
}
