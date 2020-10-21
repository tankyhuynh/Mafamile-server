package com.mafami.Mafami.api.AMI_DESIGN;

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

import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_PostEntity;
import com.mafami.Mafami.Service.AMI_DESIGN.PostService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/ami-design/post")
public class AMIDESIGN_PostAPI {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public ResponseEntity<List<AMIDESIGN_PostEntity>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIDESIGN_PostEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(postService.findOneById(id));
	}
	
	
	@PostMapping
	public ResponseEntity<AMIDESIGN_PostEntity> saveOne(@RequestBody AMIDESIGN_PostEntity entity) {
		return ResponseEntity.ok(postService.save(entity));
	}
	
	@PutMapping("/{id}")
	public AMIDESIGN_PostEntity saveOneById(@PathVariable String id, @RequestBody AMIDESIGN_PostEntity aMIDESIGN_PostEntity) {
		aMIDESIGN_PostEntity.setId(id);
		
		return postService.save(aMIDESIGN_PostEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		postService.delete(id);
	}
	
	
	
	
}
