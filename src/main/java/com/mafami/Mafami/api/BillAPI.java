package com.mafami.Mafami.api;

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

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Service.BillService;

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
@RequestMapping("/api/bill")
public class BillAPI {
	
	@Autowired
	private BillService billService;
	
	@GetMapping
	public ResponseEntity<List<BillEntity>> getAll() {
		return ResponseEntity.ok(billService.getAll());
	}
	
	@GetMapping("/{site}")
	public ResponseEntity<List<BillEntity>> getAll(@PathVariable String site) {
		return ResponseEntity.ok(billService.getAllBySite(site));
	}
	
	@GetMapping("/{site}/{id}")
	public ResponseEntity<BillEntity> getById(@PathVariable("site") String site, @PathVariable("id") String id) {
		return ResponseEntity.ok(billService.getOneById(id));
	}

	

	@PostMapping("/{site}")
	public ResponseEntity<BillEntity> saveOne(@PathVariable String site, @RequestBody BillEntity categoryEntity) {
		categoryEntity.setSite(site);
		return ResponseEntity.ok(billService.save(categoryEntity));
	}
	
	@PostMapping("/{site}/all")
	public ResponseEntity<String> saveAll(@PathVariable("site") String site, @RequestBody List<BillEntity> categoryEntity) {
		for (BillEntity entity : categoryEntity) {
			entity.setSite(site);
			billService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{site}/{id}")
	public ResponseEntity<BillEntity> update(@PathVariable("site") String site, @PathVariable("id") String id,
			@RequestBody BillEntity newEntity) {
		BillEntity oldEntity = billService.getOneById(id);

		newEntity.setId(id);
		newEntity.setSite(site);
		return ResponseEntity.ok(billService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		billService.delete(id);
	}
	
	@DeleteMapping("/{site}/all")
	public void deleteAllBySite(@PathVariable String site) {
		billService.deleteAllBySite(site);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		billService.deleteAll();
	}
	
	

}
