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
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_MenuService;

@RestController
@RequestMapping("/api/mafamile/menu")
public class MAFAMILE_MenuAPI {

	@Autowired
	private MAFAMILE_MenuService mAFAMILE_MenuService;
	
	@Autowired
	private MAFAMILE_ProductConvert mAFAMILE_ProductConvert;
	
	@GetMapping
	public ResponseEntity<List<MAFAMILE_MenuEntity>> getAll() {
		return ResponseEntity.ok(mAFAMILE_MenuService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MAFAMILE_MenuEntity> getOneById(@PathVariable String id) {
		return ResponseEntity.ok(mAFAMILE_MenuService.findOneById(id));
	}
	
	@GetMapping("/category/{categoryCode}")
	public ResponseEntity<List<MAFAMILE_MenuEntity>> getAllByCategoryCode(@PathVariable("categoryCode") String categoryCode) {
		return ResponseEntity.ok(mAFAMILE_MenuService.findAllByCategoryCode(categoryCode));
	}
	
	@PostMapping
	public ResponseEntity<MAFAMILE_MenuEntity> saveOne(@RequestBody MAFAMILE_MenuEntity mAFAMILE_MenuEntity) {
		mAFAMILE_MenuEntity.setSite("mafamile");
		return ResponseEntity.ok(mAFAMILE_MenuService.save(mAFAMILE_MenuEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveOne(@RequestBody List<MAFAMILE_MenuEntity> mAFAMILE_MenuEntity) {
		for (MAFAMILE_MenuEntity entity : mAFAMILE_MenuEntity) {
			mAFAMILE_MenuService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MAFAMILE_MenuEntity> saveOneById(@PathVariable String id, @RequestBody MAFAMILE_MenuEntity entity) {
		MAFAMILE_MenuEntity newEntity = mAFAMILE_MenuService.findOneById(id);
		newEntity = mAFAMILE_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		return ResponseEntity.ok(mAFAMILE_MenuService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		mAFAMILE_MenuService.delete(id);
	}

	
	
	
}
