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

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Service.BillService;
import com.mafami.Mafami.Utils.ExcelUtils_Bills;

@RestController
@RequestMapping("/api/excel")
public class ExcelAPI {

		@Autowired
		private BillService billService;
		
		@Autowired
		private ExcelUtils_Bills excelUtils_Bills;
	
	
		@GetMapping(value = "/bills")
		public void exportBills() throws Exception {
			
			List<BillEntity> bills = billService.getAll();
			String[] fieldName = {"ID", "Tên khách hàng", "Thông tin món", "Ngày tạo", "Ngày đặt", "Thông tin thêm", "Tổng"};
			
			excelUtils_Bills.export(fieldName, bills);
			
		}
		
		@GetMapping(value = "/bills/createdDate/{createdDate}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
		public byte[] exportBillsWith_OrderDate(@PathVariable String createdDate) throws Exception {
			
			List<BillEntity> bills = billService.getAll();
			String[] fieldName = {"ID", "Tên khách hàng", "Thông tin món", "Ngày tạo", "Ngày đặt", "Thông tin thêm", "Tổng"};
			
			DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.JAPAN);
			
//			//yyyy-mm-dd HH:mm:ss
			DateTimeFormatter clientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			LocalDate date2 = LocalDate.parse(createdDate, clientFormatter);	
			
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date d= Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
			
			List<BillEntity> listEntities = billService.getAll();
			List<BillEntity> listResult = new ArrayList<>();
			
			System.out.println("Client orderDate: " + d);
			for (BillEntity bill : listEntities) {		
				Date dbDate = bill.getOrderDate();                      
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// Use Madrid's time zone to format the date in
				df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
				LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();
				dbDate= Date.from(localDateEntity.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
				
				if (d.equals(dbDate)) {
					listResult.add(bill);
				}

			}
			byte[] res = excelUtils_Bills.export(fieldName, listResult);
			
			return res;
			
		}
		
		
		@GetMapping(value = "/bills/createdDate/{createdDate1}/{createdDate2}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
		public byte[] exportBillsWith_CreatedDateBetween(@PathVariable String createdDate1, @PathVariable String createdDate2) throws Exception {
			
			List<BillEntity> bills = billService.getAll();
			String[] fieldName = {"ID", "Tên khách hàng", "Thông tin món", "Ngày tạo", "Ngày đặt", "Thông tin thêm", "Tổng"};
			
			DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.JAPAN);

//			//yyyy-mm-dd HH:mm:ss
			DateTimeFormatter clientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

			LocalDate date1 = LocalDate.parse(createdDate1, clientFormatter);
			LocalDate date2 = LocalDate.parse(createdDate2, clientFormatter);

			ZoneId defaultZoneId = ZoneId.systemDefault();

			Date d1 = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
			Date d2 = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
			d2.setDate(d2.getDate() + 1);

			System.out.println("OrderDate 1: " + d1);
			System.out.println("OrderDate 2: " + d2);

			List<BillEntity> listEntities = billService.getAll();
			List<BillEntity> listResult = new ArrayList<>();

			System.out.println("Client orderDate: " + d2);
			
			
			bills = billService.getAllByOrderDateBetween(d1, d2);
			byte[] res = excelUtils_Bills.export(fieldName, bills);
			
			return res;
			
		}
		
		
		
		
		
		
		
		public static void main(String[] args) {
			
			String password =  "admin";
			String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			
			String passwordCheck = "stackjava.com";
			boolean check = BCrypt.checkpw(password, "$2a$12$CWsNI/IFmji46Ug3OgIYhOxJxb6oCjGdk94vNKHgCn80nBQZrNOSu");
			
			System.out.println(hash);
			System.out.println(check);
			
//			Thread thread = new Thread();
//			thread.
			
			
			
			
			
				
		}
		
		
		
		
		
		
		
	
}
