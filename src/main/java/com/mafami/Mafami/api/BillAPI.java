package com.mafami.Mafami.api;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

	@GetMapping("/orderdate/{orderDate}")
	public ResponseEntity<List<BillEntity>> getAllByOrderDate(@PathVariable String orderDate) throws Exception {
		//yyyy-mm-dd HH:mm:ss
	
//		String string = "2020-12-21T21:00:00.000+00:00";
		DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
			
//		//yyyy-mm-dd HH:mm:ss
		DateTimeFormatter clientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		LocalDate date2 = LocalDate.parse(orderDate, clientFormatter);
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d= Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
//		d = java.sql.Date.valueOf(date2);

		
		List<BillEntity> listEntities = billService.getAll();
		List<BillEntity> listResult = new ArrayList<>();
		
		System.out.println("Client orderDate: " + d);
		for (BillEntity bill : listEntities) {		
			Date dbDate = bill.getOrderDate();
			LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			dbDate= Date.from(localDateEntity.atStartOfDay(defaultZoneId).toInstant());
			
			if (d.equals(dbDate)) {
				listResult.add(bill);
			}

		}
		
		
		return ResponseEntity.ok( listResult );
//		return null;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<BillEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(billService.getOneById(id));
	}

	
	@PostMapping
	public ResponseEntity<BillEntity> saveOne(@RequestBody BillEntity categoryEntity) {
		return ResponseEntity.ok(billService.save(categoryEntity));
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveAll(@RequestBody List<BillEntity> categoryEntity) {
		for (BillEntity entity : categoryEntity) {
			billService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<BillEntity> update(@PathVariable("id") String id,
			@RequestBody BillEntity newEntity) {
		BillEntity oldEntity = billService.getOneById(id);
		newEntity.setId(id);
		return ResponseEntity.ok(billService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		billService.delete(id);
	}

	
	@DeleteMapping("/all")
	public void deleteAll() {
		billService.deleteAll();
	}
	
	

}
