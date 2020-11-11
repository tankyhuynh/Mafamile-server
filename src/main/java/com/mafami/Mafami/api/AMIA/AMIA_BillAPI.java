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

import com.mafami.Mafami.Entity.AMIA.AMIA_BillEntity;
import com.mafami.Mafami.Entity.AMIA.AMIA_CategoryEntity;
import com.mafami.Mafami.Service.AMIA.AMIA_BillService;

/**
* @author root {11:02:46 AM}:
 * @version Creation time: Nov 11, 2020 11:02:46 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@RestController
@RequestMapping("/api/amia/bill")
public class AMIA_BillAPI {
	
	@Autowired
	private AMIA_BillService amia_BillService;
	
	@GetMapping("/{id}")
	public ResponseEntity<AMIA_BillEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(amia_BillService.getOneById(id));
	}

	@GetMapping
	public ResponseEntity<List<AMIA_BillEntity>> getAll() {
		return ResponseEntity.ok(amia_BillService.getAll());
	}

	@PostMapping
	public ResponseEntity<AMIA_BillEntity> saveOne(@RequestBody AMIA_BillEntity categoryEntity) {
		categoryEntity.setSite("amia");
		return ResponseEntity.ok(amia_BillService.save(categoryEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveAll(@RequestBody List<AMIA_BillEntity> categoryEntity) {
		for (AMIA_BillEntity entity : categoryEntity) {
			entity.setSite("amia");
			amia_BillService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<AMIA_BillEntity> update(@PathVariable("id") String id,
			@RequestBody AMIA_BillEntity newEntity) {
		AMIA_BillEntity oldEntity = amia_BillService.getOneById(id);

		newEntity.setId(id);
		return ResponseEntity.ok(amia_BillService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		amia_BillService.delete(id);
	}
	
	

}
