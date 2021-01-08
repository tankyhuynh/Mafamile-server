package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.ComboFoodConvert;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Entity.ComboFoodEntity;
import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.Service.BillService;
import com.mafami.Mafami.Service.ComboFoodService;
import com.mafami.Mafami.Service.CustomerService;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.Utils.MailUtils;
import com.mafami.Mafami.model.FoodInformationModel;

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

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	@Autowired
	private MailUtils mailUtils;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ComboFoodService comboFoodService;
	
	@Autowired
	private ComboFoodConvert comboFoodConvert;
	
	private String admin_mail = "5f89a8a1f5cdd900414ae8dc";

	@GetMapping
	public ResponseEntity<List<BillEntity>> getAll() {
		List<BillEntity> listEntities = billService.getAll(); 
		return ResponseEntity.ok(listEntities);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<BillEntity>> getAll(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "ten", required = false) String ten, @RequestParam(value = "sdt", required = false) String sdt) {
		
		BillEntity billEntity_ID = billService.getOneById(id);
		List<BillEntity> listBills = new ArrayList<>();
		List<CustomerEntity> customerEntity_ByPhone = new ArrayList<>();
		List<CustomerEntity> customerEntity_ByName = new ArrayList<>();
		
		try {
			customerEntity_ByPhone = customerService.findAllByPhone(sdt);
			customerEntity_ByName  = customerService.findAllByName(ten);
		} catch (Exception e) {
			
		}

		if(billEntity_ID != null) {
			listBills.add(billEntity_ID);
		}
		else if(customerEntity_ByPhone.size() >=1 ) {
			System.out.println("Search By Phone size >= 1");
			for (CustomerEntity customerEntity : customerEntity_ByPhone) {
				listBills.addAll( billService.getAllByCustomer(customerEntity) );
			}
		}
		else {
			for (CustomerEntity customer : customerEntity_ByName) {
				listBills.addAll(billService.getAllByCustomer(customer));
			}
		}
		
		
		
		
		return ResponseEntity.ok(listBills);
	}
	

	@GetMapping("/page/{numberOfPage}")
	public Page<BillEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return billService.getAllByPage(numberOfPage);
	}

	@GetMapping("/orderDate/{orderDate}")
	public ResponseEntity<List<BillEntity>> getAllByCreatedDate(@PathVariable String orderDate) throws Exception {
		DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.JAPAN);

//		//yyyy-mm-dd HH:mm:ss
		DateTimeFormatter clientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		LocalDate date2 = LocalDate.parse(orderDate, clientFormatter);

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());

		List<BillEntity> listEntities = billService.getAll();
		List<BillEntity> listResult = new ArrayList<>();

		System.out.println("Client orderDate: " + d);
		for (BillEntity bill : listEntities) {
			Date dbDate = bill.getOrderDate();

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));

			System.out.println(
					"ID: " + bill.getId() + " Entity Date Before: " + dbDate + " format: " + df.format(dbDate));
			LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();

			dbDate = Date.from(localDateEntity.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
			System.out.println("Entity Date: " + dbDate);
			System.out.println();

			if (d.equals(dbDate)) {
				listResult.add(bill);
			}

		}

		return ResponseEntity.ok(listResult);
	}

	@GetMapping("/orderDate/{orderDate1}/{orderDate2}")
	public ResponseEntity<List<BillEntity>> getAllByCreatedDateBetween(
			@PathVariable("orderDate1") String orderDate1, @PathVariable("orderDate2") String orderDate2)
			throws Exception {
		DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.JAPAN);

//		//yyyy-mm-dd HH:mm:ss
		DateTimeFormatter clientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		LocalDate date1 = LocalDate.parse(orderDate1, clientFormatter);
		LocalDate date2 = LocalDate.parse(orderDate2, clientFormatter);

		ZoneId defaultZoneId = ZoneId.systemDefault();

		Date d1 = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
		Date d2 = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
		d2.setDate(d2.getDate() + 1);

		System.out.println("OrderDate 1: " + d1);
		System.out.println("OrderDate 2: " + d2);

		List<BillEntity> listEntities = billService.getAll();
		List<BillEntity> listResult = new ArrayList<>();

		System.out.println("Client orderDate: " + d2);
		for (BillEntity bill : listEntities) {
			Date dbDate = bill.getOrderDate();

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));

			System.out.println(
					"ID: " + bill.getId() + " Entity Date Before: " + dbDate + " format: " + df.format(dbDate));
			LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate();

			dbDate = Date.from(localDateEntity.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
			System.out.println("Entity Date: " + dbDate);
			System.out.println();

		}

		return ResponseEntity.ok(billService.getAllByOrderDateBetween(d1, d2));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BillEntity> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(billService.getOneById(id));
	}

	@GetMapping("/verifyBill")
	public ResponseEntity<String> update(@RequestParam String id) throws Exception {

		BillEntity billEntity = billService.getOneById(id);
		billEntity.setConfirmed(true);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xác nhận đơn hàng " + billEntity.getId() + " lúc "
				+ (df.parse(sf_log.format(Calendar.getInstance().getTime())));

		String customerEmail = billEntity.getCustomerInformation().getEmail();
		mailUtils.sendUpdateBill_Admin(billEntity, "Bạn vừa xác nhận đơn hàng",
				"Đơn hàng <b>" + billEntity.getId() + " </b> đã được xác nhận", "Một ngày tốt lành");
		mailUtils.sendUpdateBill_Customer(customerEmail, billEntity, "Bạn vừa đặt đơn hàng của Mafamile",
				"Đơn hàng của bạn đã được xác nhận", "Một ngày tốt lành");

		logEntity.setContent(content);
		logService.save(logEntity);
		billService.save(billEntity);

		return ResponseEntity.ok("Bạn đã xác nhận đơn hàng thành công");
	}

	@PostMapping
	public ResponseEntity<BillEntity> saveOne(@RequestBody BillEntity billEntity, @RequestParam(required = false) String comboId) throws Exception {
		Date dbDate = billEntity.getOrderDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));

		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));

		System.out.println("Before format: " + dbDate);
		System.out.println("After format: " + df.format(dbDate));
		try {
			billEntity.setOrderDate(df.parse(sf.format(dbDate)));
		} catch (Exception e) {
			System.out.println(e);
		}

		String customerEmail = billEntity.getCustomerInformation().getEmail();
		System.out.println("Customer Email: " + customerEmail);

		String customerName = billEntity.getCustomerInformation().getName();
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String username = (customerName != null) ? customerName : "Customer";
		String content = customerName + " đã đặt đơn hàng " + billEntity.getId() + " lúc "
				+ (df.parse(sf_log.format(Calendar.getInstance().getTime())));

		mailUtils.sendAddBill_Admin(billEntity, "Có đơn hàng mới",
				"Đơn hàng <b>" + billEntity.getId() + "</b> đang chờ xác nhận", "Một ngày tốt lành");
		mailUtils.sendAddBill_Customer(customerEmail, billEntity, "Bạn vừa đặt đơn hàng của Mafamile",
				"Đơn hàng của bạn đang chờ xác nhận", "Một ngày tốt lành");

		logEntity.setContent(content);
		logService.save(logEntity);
		
		List<CustomerEntity> customerEntity = customerService.findAllByName(billEntity.getCustomerInformation().getName());
		boolean check = false;
		for (CustomerEntity customer : customerEntity) {
			if ( customer.equals(billEntity.getCustomerInformation()) ) {
				check = true;
			}
		}
		
		if (!check) {
			customerService.save(billEntity.getCustomerInformation());
		}
		
		ComboFoodEntity comboFoodEntity = comboFoodService.findOneById(comboId);
		MenuEntity menuEntity = comboFoodConvert.fromCombo_To_Menu(comboFoodEntity);
		billEntity.getFoodInformation().add( new FoodInformationModel(menuEntity, "", 1) );
		

		return ResponseEntity.ok(billService.save(billEntity));
	}

	@PostMapping("/all")
	public ResponseEntity<String> saveAll(@RequestBody List<BillEntity> entities) {
		for (BillEntity entity : entities) {
			billService.save(entity);
			
			List<CustomerEntity> customerEntity = customerService.findAllByName(entity.getCustomerInformation().getName());
//			boolean check = false;
//			for (CustomerEntity customer : customerEntity) {
//				if ( customer.equals(entity.getCustomerInformation()) ) {
//					check = true;
//				}
//			}
			
			customerService.save(entity.getCustomerInformation());
			
			
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<BillEntity> update(@PathVariable("id") String id, @RequestBody BillEntity newEntity)
			throws Exception {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		BillEntity oldEntity = billService.getOneById(id);
		newEntity.setId(id);

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xác nhận đơn hàng " + newEntity.getId() + " lúc "
				+ (df.parse(sf_log.format(Calendar.getInstance().getTime())));

		String customerEmail = newEntity.getCustomerInformation().getEmail();
		if (newEntity.isConfirmed()) {
			mailUtils.sendUpdateBill_Admin( newEntity, "Bạn vừa xác nhận đơn hàng",
					"Đơn hàng <b>" + newEntity.getId() + " </b> đã được xác nhận", "Một ngày tốt lành");
			try {
				mailUtils.sendUpdateBill_Customer(customerEmail, newEntity, "Thông tin đơn hàng " + newEntity.getId(),
						"Đơn hàng của bạn đã được xác nhận", "Một ngày tốt lành");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		logEntity.setContent(content);
		logService.save(logEntity);

		return ResponseEntity.ok(billService.save(newEntity));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) throws Exception {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa đơn hàng " + id + " lúc "
				+ (df.parse(sf_log.format(Calendar.getInstance().getTime())));
		logEntity.setContent(content);
		logService.save(logEntity);

		billService.delete(id);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		billService.deleteAll();
	}

}
