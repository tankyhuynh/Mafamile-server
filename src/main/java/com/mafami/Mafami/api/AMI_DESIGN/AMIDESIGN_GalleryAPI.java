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

import com.mafami.Mafami.Entity.AMI_DESIGN.AMIDESIGN_GalleryEntity;
import com.mafami.Mafami.Service.AMIA_DESIGN.GalleryService;
import com.mafami.Mafami.Utils.FileUtils_TanKy;

@RestController
@RequestMapping("/api/ami-design/gallery")
public class AMIDESIGN_GalleryAPI {

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUtils_TanKy fileUtils;
	
	@GetMapping
	public ResponseEntity<List<AMIDESIGN_GalleryEntity>> getAll() {
		return ResponseEntity.ok(galleryService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIDESIGN_GalleryEntity> getOne(@PathVariable("id") String id) {
		return ResponseEntity.ok(galleryService.findOneById(id));
	}
	
	
	@PostMapping
	public ResponseEntity<AMIDESIGN_GalleryEntity> saveOne(@RequestBody AMIDESIGN_GalleryEntity entity) {
		List<String> listImages = new ArrayList<>();
		for (String item : entity.getImages()) {
			String URL = fileUtils.decoder(item, "outputFile");
			listImages.add(URL);
		}
		entity.setImages(listImages);
		
		return ResponseEntity.ok(galleryService.save(entity));
	}
	
	@PutMapping("/{id}")
	public AMIDESIGN_GalleryEntity saveOneById(@PathVariable String id, @RequestBody AMIDESIGN_GalleryEntity aMIDESIGN_GalleryEntity) {
		aMIDESIGN_GalleryEntity.setId(id);
		
		return galleryService.save(aMIDESIGN_GalleryEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		galleryService.delete(id);
	}
	
	
	
	
}
