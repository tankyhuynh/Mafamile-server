package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.RecruitmentEntity;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.RecruitmentService;
import com.mafami.Mafami.model.RecruitTimeModel;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentAPI {

	@Autowired
	private RecruitmentService recruitmentService;
	
	@Autowired
	private LogService logService;

	@GetMapping
	public List<RecruitmentEntity> getAll() {
		return recruitmentService.findAll();
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<RecruitmentEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return recruitmentService.findAllByPage(numberOfPage);
	}

	@GetMapping("/{site}")
	public List<RecruitmentEntity> getAllBySite(@PathVariable String site) {
		return recruitmentService.findAllBySite(site);
	}

	@GetMapping("/{site}/{id}")
	public ResponseEntity<RecruitmentEntity> getOneById( @PathVariable("id") String id) {
		RecruitmentEntity logEntity = recruitmentService.findOneById(id);
		if (logEntity != null) {
			return ResponseEntity.ok(recruitmentService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/{site}")
	public RecruitmentEntity saveOneBySite(@PathVariable String site, @RequestBody RecruitmentEntity recruitmentEntity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf_log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf_log.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		Date startDate = recruitmentEntity.getTime().getStartDate();
		Date endDate = recruitmentEntity.getTime().getEndDate();
		RecruitTimeModel timeModel = new RecruitTimeModel( df.parse(sf_log.format(startDate)),  df.parse(sf_log.format(endDate)) );
		
		recruitmentEntity.setTime(timeModel);
		recruitmentEntity.setSite(site);
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm tin tuyển dụng " + recruitmentEntity.getTitle() + " lúc " + ( df.parse(sf_log.format(Calendar.getInstance().getTime())) ) + " vào tuyển dụng của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return recruitmentService.save(recruitmentEntity); 
	}

	@PutMapping("/{site}/{id}")
	public ResponseEntity<RecruitmentEntity> saveOneById( @PathVariable("site") String site ,@PathVariable("id") String id, @RequestBody RecruitmentEntity newEntity) throws Exception {
		RecruitmentEntity oldEntity = recruitmentService.findOneById(id);
		newEntity.setId(id);
		newEntity.setSite(site);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin tuyển dụng " + newEntity.getTitle() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + " trong tuyển dụng của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		if (oldEntity != null)
			return ResponseEntity.ok(recruitmentService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xóa tin tuyển dụng " + id + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		recruitmentService.delete(id);
	}
	
	@DeleteMapping("/{site}/{id}")
	public void deleteOneByIdAndSite(@PathVariable("site") String site, @PathVariable("id") String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã xóa tin tuyển dụng " + id + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		recruitmentService.delete(id);
	}

	@DeleteMapping("/{site}/all")
	public void deleteAllBySite(@PathVariable String site) {
		recruitmentService.deleteAllBySite(site);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		recruitmentService.deleteAll();
	}

}
