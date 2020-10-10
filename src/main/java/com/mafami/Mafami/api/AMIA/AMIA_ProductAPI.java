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
import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;
import com.mafami.Mafami.Service.AMIA.AMIA_ProductService;


@RestController
@RequestMapping("/api/amia/products")
public class AMIA_ProductAPI {

	@Autowired
	private AMIA_ProductService aMIA_ProductService;
	
	@Autowired
	private AMIA_ProductConvert aMIA_ProductConvert;
	
	@GetMapping
	public ResponseEntity<List<AMIA_ProductEntity>> getAll() {
		return ResponseEntity.ok(aMIA_ProductService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIA_ProductEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(aMIA_ProductService.findOneById(id));
	}
	
	@GetMapping("/category/{categoryCode}")
	public ResponseEntity<List<AMIA_ProductEntity>> getAllByCategoryCode(@PathVariable("categoryCode") String categoryCode) {
		return ResponseEntity.ok(aMIA_ProductService.findAllByCategoryCode(categoryCode));
	}
	
	@PostMapping
	public ResponseEntity<AMIA_ProductEntity> saveOne(@RequestBody AMIA_ProductEntity aMIA_ProductEntity) {
		return ResponseEntity.ok(aMIA_ProductService.save(aMIA_ProductEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveOne(@RequestBody List<AMIA_ProductEntity> aMIA_ProductEntity) {
		for (AMIA_ProductEntity entity : aMIA_ProductEntity) {
			aMIA_ProductService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AMIA_ProductEntity> saveOneById(@PathVariable String id, @RequestBody AMIA_ProductEntity entity) {
		AMIA_ProductEntity newEntity = aMIA_ProductService.findOneById(id);
		newEntity = aMIA_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		return ResponseEntity.ok(aMIA_ProductService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		aMIA_ProductService.delete(id);
	}

	
	
	
}
