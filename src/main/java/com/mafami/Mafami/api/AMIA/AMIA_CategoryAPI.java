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

import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;
import com.mafami.Mafami.Service.AMIA.AMIA_CategoryService;

/**
* @author root {8:39:53 AM}:
 * @version Creation time: Oct 10, 2020 8:39:53 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
@RequestMapping("/api/amia/category")
public class AMIA_CategoryAPI {

	@Autowired
	private AMIA_CategoryService amia_CategoryService;

	@GetMapping("/{id}")
	public ResponseEntity<AMIA_CategoryEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(amia_CategoryService.getOneById(id));
	}

	@GetMapping
	public ResponseEntity<List<AMIA_CategoryEntity>> getAll() {
		return ResponseEntity.ok(amia_CategoryService.getAll());
	}

	@PostMapping
	public ResponseEntity<AMIA_CategoryEntity> save(@RequestBody AMIA_CategoryEntity categoryEntity) {
		return ResponseEntity.ok(amia_CategoryService.save(categoryEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> save(@RequestBody List<AMIA_CategoryEntity> categoryEntity) {
		for (AMIA_CategoryEntity entity : categoryEntity) {
			amia_CategoryService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<AMIA_CategoryEntity> update(@PathVariable("id") String id,
			@RequestBody AMIA_CategoryEntity newEntity) {
		AMIA_CategoryEntity oldEntity = amia_CategoryService.getOneById(id);

		newEntity.setId(id);
		return ResponseEntity.ok(amia_CategoryService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		amia_CategoryService.delete(id);
	}
	
	
	
	
	

}
