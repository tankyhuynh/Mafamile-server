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

import com.mafami.Mafami.Entity.AMI_DESIGN.GalleryEntity;
import com.mafami.Mafami.Service.AMIA_DESIGN.GalleryService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/amia-design/gallery")
public class GalleryAPI {

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public ResponseEntity<List<GalleryEntity>> getAll() {
		return ResponseEntity.ok(galleryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GalleryEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(galleryService.findOneById(id));
	}
	
	
	@PostMapping
	public ResponseEntity<GalleryEntity> saveOne(@RequestBody GalleryEntity entity) {
		List<String> listImages = new ArrayList<>();
		for (String item : entity.getImages()) {
			String URL = fileUtils.decoder(item, "outputFile");
			listImages.add(URL);
		}
		entity.setImages(listImages);
		
		return ResponseEntity.ok(galleryService.save(entity));
	}
	
	@PutMapping("/{id}")
	public GalleryEntity saveOneById(@PathVariable String id, @RequestBody GalleryEntity galleryEntity) {
		galleryEntity.setId(id);
		
		return galleryService.save(galleryEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		galleryService.delete(id);
	}
	
	
	
	
}
