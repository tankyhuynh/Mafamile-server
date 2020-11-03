package com.mafami.Mafami.api.AMIA;

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

import com.mafami.Mafami.Convert.AMIA.AMIA_ProductConvert;
import com.mafami.Mafami.Entity.AMIA.AMIA_MenuEntity;
import com.mafami.Mafami.Service.AMIA.AMIA_MenuService;
import com.mafami.Mafami.Utils.FileUtils;


@RestController
@RequestMapping("/api/amia/menu")
public class AMIA_MenuAPI {

	@Autowired
	private AMIA_MenuService aMIA_MenuService;
	
	@Autowired
	private AMIA_ProductConvert aMIA_ProductConvert;
	
	@Autowired
	private FileUtils fileUtils;
	
	@GetMapping
	public ResponseEntity<List<AMIA_MenuEntity>> getAll() {
		return ResponseEntity.ok(aMIA_MenuService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIA_MenuEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(aMIA_MenuService.findOneById(id));
	}
	
	@GetMapping("/category/{categoryCode}")
	public ResponseEntity<List<AMIA_MenuEntity>> getAllByCategoryCode(@PathVariable("categoryCode") String categoryCode) {
		return ResponseEntity.ok(aMIA_MenuService.findAllByCategoryCode(categoryCode));
	}
	
	@PostMapping
	public ResponseEntity<AMIA_MenuEntity> saveOne(@RequestBody AMIA_MenuEntity aMIA_MenuEntity) {
		String URL = fileUtils.decoder(aMIA_MenuEntity.getImage(), "ImageAPI");
		aMIA_MenuEntity.setImage(URL);
		aMIA_MenuEntity.setSite("amia");
		return ResponseEntity.ok(aMIA_MenuService.save(aMIA_MenuEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveOne(@RequestBody List<AMIA_MenuEntity> aMIA_MenuEntity) {
		for (AMIA_MenuEntity entity : aMIA_MenuEntity) {
			aMIA_MenuService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AMIA_MenuEntity> saveOneById(@PathVariable String id, @RequestBody AMIA_MenuEntity entity) {
		AMIA_MenuEntity newEntity = aMIA_MenuService.findOneById(id);
		newEntity = aMIA_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		return ResponseEntity.ok(aMIA_MenuService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		aMIA_MenuService.delete(id);
	}

	
	
	
}
