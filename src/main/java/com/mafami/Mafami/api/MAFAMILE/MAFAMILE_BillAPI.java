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

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_BillEntity;
import com.mafami.Mafami.Service.MAFAMILE.MAFAMILE_BillService;

/**
* @author root {11:06:55 AM}:
 * @version Creation time: Nov 11, 2020 11:06:55 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@RestController
@RequestMapping("/api/mafamile/bill")
public class MAFAMILE_BillAPI {

	@Autowired
	private MAFAMILE_BillService mafamile_BillService;

	@GetMapping("/{id}")
	public ResponseEntity<MAFAMILE_BillEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(mafamile_BillService.getOneById(id));
	}

	@GetMapping
	public ResponseEntity<List<MAFAMILE_BillEntity>> getAll() {
		return ResponseEntity.ok(mafamile_BillService.getAll());
	}

	@PostMapping
	public ResponseEntity<MAFAMILE_BillEntity> saveOne(@RequestBody MAFAMILE_BillEntity categoryEntity) {
		categoryEntity.setSite("amia");
		return ResponseEntity.ok(mafamile_BillService.save(categoryEntity));
	}

	@PostMapping("/all")
	public ResponseEntity<String> saveAll(@RequestBody List<MAFAMILE_BillEntity> categoryEntity) {
		for (MAFAMILE_BillEntity entity : categoryEntity) {
			entity.setSite("amia");
			mafamile_BillService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<MAFAMILE_BillEntity> update(@PathVariable("id") String id,
			@RequestBody MAFAMILE_BillEntity newEntity) {
		MAFAMILE_BillEntity oldEntity = mafamile_BillService.getOneById(id);

		newEntity.setId(id);
		return ResponseEntity.ok(mafamile_BillService.save(newEntity));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		mafamile_BillService.delete(id);
	}

}
