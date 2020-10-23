package com.mafami.Mafami.api.AMIA;

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

import com.mafami.Mafami.Entity.AMIA.AMIA_PostEntity;
import com.mafami.Mafami.Service.AMIA.AMIA_PostService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/amia/post")
public class AMIA_PostAPI {

	@Autowired
	private AMIA_PostService amia_PostService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public List<AMIA_PostEntity> getAll() {
		return amia_PostService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIA_PostEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(amia_PostService.findOneById(id));
	}
	
	@PostMapping
	public ResponseEntity<AMIA_PostEntity> saveOne(@RequestBody AMIA_PostEntity entity) {
		return ResponseEntity.ok(amia_PostService.save(entity));
	}
	
	@PutMapping("/{id}")
	public AMIA_PostEntity saveOneById(@PathVariable String id, @RequestBody AMIA_PostEntity amia_PostEntity) {
		amia_PostEntity.setId(id);
		
		return amia_PostService.save(amia_PostEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		amia_PostService.delete(id);
	}
	
	
	
	
	
	
	
}
