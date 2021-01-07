package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.ComboFoodEntity;
import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Service.ComboFoodService;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Utils.FileUtils;
import com.mafami.Mafami.model.PriceModel;

@RestController
@RequestMapping("/api/combo")
public class ComboFoodAPI {

	@Autowired
	private ComboFoodService comboFoodService;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private LogService logService;
	
	@GetMapping
	public List<ComboFoodEntity> getAll() {
		return comboFoodService.findAll();
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<ComboFoodEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return comboFoodService.findAllByPage(numberOfPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComboFoodEntity> getOneById(@PathVariable String id) {
		ComboFoodEntity comboFoodEntity = comboFoodService.findOneById(id);
		if (comboFoodEntity != null) {
			return ResponseEntity.ok(comboFoodService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ComboFoodEntity saveOne(@RequestBody ComboFoodEntity comboEntity) throws Exception {
		try {
			String URL = fileUtils.decoder(comboEntity.getThumbnail(), "ImageAPI");
			comboEntity.setThumbnail(URL);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			comboEntity.setThumbnail("https://res.cloudinary.com/dbqzpdgpi/image/upload/v1596088950/MrVans/MrVans_Image_df9p6s.png");
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm combo " + comboEntity.getName()+ " lúc " + ( df.parse(sf.format(Calendar.getInstance().getTime())) );
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return comboFoodService.save(comboEntity);
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> saveAll(@RequestBody List<ComboFoodEntity> comboFoodEntities) {
		List<PriceModel> prices = new ArrayList<>();
		for (ComboFoodEntity entity : comboFoodEntities) {
			try {
				String URL = fileUtils.decoder(entity.getThumbnail(), "ImageAPI");
				entity.setThumbnail(URL);
			} catch (Exception e) {
				System.out.println("Exception: " + e);
				entity.setThumbnail("https://res.cloudinary.com/dbqzpdgpi/image/upload/v1596088950/MrVans/MrVans_Image_df9p6s.png");
			}
			comboFoodService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComboFoodEntity> saveOneById(@PathVariable String id, @RequestBody(required = false) ComboFoodEntity newEntity) throws Exception {
		ComboFoodEntity oldEntity = comboFoodService.findOneById(id);
		newEntity.setId(id);
		
		if(newEntity.getThumbnail() != null) {
			String URL = fileUtils.decoder(newEntity.getThumbnail(), "ImageAPI");
			newEntity.setThumbnail(URL);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa combo " + newEntity.getName() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) );
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		if (oldEntity != null)
			return ResponseEntity.ok(comboFoodService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		ComboFoodEntity comboFoodEntity = comboFoodService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa combo " + comboFoodEntity.getName() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		comboFoodService.delete(id);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		comboFoodService.deleteAll();
	}
	
}
