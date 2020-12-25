package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.PromotionEntity;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.PromotionService;
import com.mafami.Mafami.Utils.FileUtils;

@RestController
@RequestMapping("/api/promotion")
public class PromotionAPI {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private FileUtils fileUtils;

	@GetMapping
	public List<PromotionEntity> getAll() {
		return promotionService.findAll();
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<PromotionEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return promotionService.findAllByPage(numberOfPage);
	}

	@GetMapping("/{site}")
	public List<PromotionEntity> getAllBySite(@PathVariable String site) {
		return promotionService.findAllBySite(site);
	}

	@GetMapping("/{site}/{id}")
	public ResponseEntity<PromotionEntity> getOneById( @PathVariable("id") String id) {
		PromotionEntity logEntity = promotionService.findOneById(id);
		if (logEntity != null) {
			return ResponseEntity.ok(promotionService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/{site}")
	public PromotionEntity saveOneBySite(@PathVariable String site, @RequestBody PromotionEntity promotionEntity) throws Exception {
		promotionEntity.setSite(site);
		
		try {
			String URL = fileUtils.decoder(promotionEntity.getThumbnail(), "outputFile");
			promotionEntity.setThumbnail(URL);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm ưu đãi " + promotionEntity.getTitle() + " lúc " + ( df.parse(sf.format(Calendar.getInstance().getTime())) ) + " vào ưu đãi của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return promotionService.save(promotionEntity);
	}

	@PutMapping("/{site}/{id}")
	public ResponseEntity<PromotionEntity> saveOneById( @PathVariable("site") String site ,@PathVariable("id") String id, @RequestBody PromotionEntity newEntity) throws Exception {
		PromotionEntity oldEntity = promotionService.findOneById(id);
		newEntity.setId(id);
		newEntity.setSite(site);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin ưu đãi " + newEntity.getTitle() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + " trong ưu đãi của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		if (oldEntity != null)
			return ResponseEntity.ok(promotionService.save(newEntity));

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
		String content = "Admin " + " đã xóa ưu đãi " + id + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		promotionService.delete(id);
	}
	
	@DeleteMapping("/{site}{id}")
	public void deleteOneByIdAndSite(@PathVariable String id, @RequestBody(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa ưu đãi " + id + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		promotionService.delete(id);
	}

	@DeleteMapping("/{site}/all")
	public void deleteAllBySite(@PathVariable String site) {
		promotionService.deleteAllBySite(site);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		promotionService.deleteAll();
	}

}
