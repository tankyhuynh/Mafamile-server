package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @author root {1:56:37 PM}:
 * @version Creation time: Nov 8, 2020 1:56:37 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Service.ContactService;
import com.mafami.Mafami.Service.LogService;

@RestController
@RequestMapping("/api/contact")
public class ContactAPI {

	@Autowired
	private ContactService contactService;

	@Autowired
	private LogService logService;

	@GetMapping
	public List<ContactEntity> getAll() {
		return contactService.findAll();
	}

	@GetMapping("/page/{numberOfPage}")
	public Page<ContactEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return contactService.findAllByPage(numberOfPage);
	}

	@GetMapping("/{site}")
	public List<ContactEntity> getAllBySite(@PathVariable String site) {
		return contactService.findAllBySite(site);
	}

	@GetMapping("/{site}/{id}")
	public ResponseEntity<ContactEntity> getOneById(@PathVariable("id") String id) {
		ContactEntity logEntity = contactService.findOneById(id);
		if (logEntity != null) {
			return ResponseEntity.ok(contactService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/{site}")
	public ContactEntity saveOneBySite(@PathVariable String site, @RequestBody ContactEntity contactEntity)
			throws Exception {
		contactEntity.setSite(site);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		SimpleDateFormat sf_entity = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_entity.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));

		Date createdDate = df.parse(sf.format(Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT-7")).getTime()));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String customerName = contactEntity.getCustomerName();
		String content = customerName + " đã thêm liên hệ " + contactEntity.getId() + " lúc " + (createdDate)
				+ " vào liên hệ của " + site;

		logEntity.setContent(content);
		logService.save(logEntity);

		contactEntity.setTime(Calendar.getInstance().getTime());
		return contactService.save(contactEntity);
	}

	@PutMapping("/{site}/{id}")
	public ResponseEntity<ContactEntity> saveOneById(@PathVariable("site") String site, @PathVariable("id") String id,
			@RequestBody ContactEntity newEntity) throws Exception {
		ContactEntity oldEntity = contactService.findOneById(id);
		newEntity.setId(id);
		newEntity.setSite(site);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin liên hệ " + newEntity.getId() + " lúc "
				+ (df.parse(sf.format((Calendar.getInstance().getTime())))) + " trong liên hệ của " + site;

		logEntity.setContent(content);
		logService.save(logEntity);

		if (oldEntity != null)
			return ResponseEntity.ok(contactService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestBody(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xóa liên hệ " + id + " lúc "
				+ (df.parse(sf.format((Calendar.getInstance().getTime())))) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);

		contactService.delete(id);
	}
	
	@DeleteMapping("/{site}/{id}")
	public void deleteOneByIdAndSite(@PathVariable String id, @RequestBody(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));

		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xóa liên hệ " + id + " lúc "
				+ (df.parse(sf.format((Calendar.getInstance().getTime())))) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);

		contactService.delete(id);
	}

	@DeleteMapping("/{site}/all")
	public void deleteAllBySite(@PathVariable String site) {
		contactService.deleteAllBySite(site);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		contactService.deleteAll();
	}

}
