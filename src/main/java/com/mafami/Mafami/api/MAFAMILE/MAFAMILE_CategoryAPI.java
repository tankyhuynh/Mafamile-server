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
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CategoryEntity;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_CategoryService;



/**
* @author root {9:24:01 AM}:
 * @version Creation time: Oct 10, 2020 9:24:01 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
@RequestMapping("/api/mafamile/category")
public class MAFAMILE_CategoryAPI {

	@Autowired
	private MAFAMILE_CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MAFAMILE_CategoryEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(categoryService.getOneById(id));
	}

	@GetMapping
	public ResponseEntity<List<MAFAMILE_CategoryEntity>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}

	@PostMapping
	public ResponseEntity<MAFAMILE_CategoryEntity> save(@RequestBody MAFAMILE_CategoryEntity categoryEntity) {
		return ResponseEntity.ok(categoryService.save(categoryEntity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MAFAMILE_CategoryEntity> update(@PathVariable("id") String id,
			@RequestBody MAFAMILE_CategoryEntity newEntity) {
		MAFAMILE_CategoryEntity oldEntity = categoryService.getOneById(id);

		newEntity.setId(id);
		return ResponseEntity.ok(categoryService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		categoryService.delete(id);
	}
	
}
