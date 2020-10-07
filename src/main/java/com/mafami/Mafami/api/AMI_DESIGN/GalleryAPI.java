package com.mafami.Mafami.api.AMI_DESIGN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.AMI_DESIGN.GalleryEntity;
import com.mafami.Mafami.Service.AMIA_DESIGN.GalleryService;

@RestController
@RequestMapping("/api/amia-design/gallery")
public class GalleryAPI {

	@Autowired
	private GalleryService galleryService;
	
	@GetMapping
	public ResponseEntity<List<GalleryEntity>> getAll() {
		return ResponseEntity.ok(galleryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GalleryEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(galleryService.findOneById(id));
	}
	
	
}
