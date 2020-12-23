package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
		List<BillEntity> listEntities = billService.getAll();
		
		
		for (BillEntity bill : listEntities) {
			Date dbDate = bill.getOrderDate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
			System.out.println( "Before format: " + dbDate );
			System.out.println( df.format(dbDate) );
			try {
				System.out.println("After format: " + df.parse(sf.format(dbDate)) );
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println();
			
		}
		
		return ResponseEntity.ok( listEntities );
	}

	@GetMapping("/orderdate/{orderDate}")
	public ResponseEntity<List<BillEntity>> getAllByOrderDate(@PathVariable String orderDate) throws Exception {
		//yyyy-mm-dd HH:mm:ss
	
//		String string = "2020-12-21T21:00:00.000+00:00";
		DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.JAPAN);
			
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
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
			
			System.out.println("ID: " + bill.getId() +" Entity Date Before: " + dbDate + " format: " + df.format(dbDate));
			LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
	
			dbDate= Date.from(localDateEntity.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
			System.out.println("Entity Date: " + dbDate);
			System.out.println();
			
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
	public ResponseEntity<BillEntity> saveOne(@RequestBody BillEntity billEntity) {
//		Date dbDate = billEntity.getOrderDate();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
//
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
//		System.out.println( "Before format: " +dbDate );
//		System.out.println( "After format: " +df.format(dbDate) );
//		try {
//			billEntity.setOrderDate( df.parse(sf.format(dbDate)) );
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		return ResponseEntity.ok(billService.save(billEntity));
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
