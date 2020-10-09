package com.mafami.Mafami.api.MAFAMILE;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.MAFAMILE.MAFAMILE_ProductConvert;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_ProductEntity;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_ProductService;

@RestController
@RequestMapping("/api/mafamile/products")
public class MAFAMILE_ProductAPI {

	@Autowired
	private MAFAMILE_ProductService mAFAMILE_ProductService;
	
	@Autowired
	private MAFAMILE_ProductConvert mAFAMILE_ProductConvert;
	
	@GetMapping
	public ResponseEntity<List<MAFAMILE_ProductEntity>> getAll() {
		return ResponseEntity.ok(mAFAMILE_ProductService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MAFAMILE_ProductEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(mAFAMILE_ProductService.findOneById(id));
	}
	
	@PostMapping
	public ResponseEntity<MAFAMILE_ProductEntity> saveOne(@RequestBody MAFAMILE_ProductEntity mAFAMILE_ProductEntity) {
		return ResponseEntity.ok(mAFAMILE_ProductService.save(mAFAMILE_ProductEntity));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MAFAMILE_ProductEntity> saveOneById(@PathVariable String id, @RequestBody MAFAMILE_ProductEntity entity) {
		MAFAMILE_ProductEntity newEntity = mAFAMILE_ProductService.findOneById(id);
		newEntity = mAFAMILE_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		return ResponseEntity.ok(mAFAMILE_ProductService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		mAFAMILE_ProductService.delete(id);
	}

	
	
	
}
