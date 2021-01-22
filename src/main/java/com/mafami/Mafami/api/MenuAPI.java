package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

import com.mafami.Mafami.Convert.AMIA.AMIA_ProductConvert;
import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.LogEntity;
import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.Service.CategoryService;
import com.mafami.Mafami.Service.LogService;
import com.mafami.Mafami.Service.MenuService;
import com.mafami.Mafami.Utils.FileUtils;
import com.mafami.Mafami.model.PriceModel;


@RestController
@RequestMapping("/api/menu")
public class MenuAPI {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private AMIA_ProductConvert aMIA_ProductConvert;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<MenuEntity>> getAll() {
		return ResponseEntity.ok(menuService.getAll());
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<MenuEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return menuService.findAllByPage(numberOfPage);
	}
	
	@GetMapping("/{site}")
	public ResponseEntity<List<MenuEntity>> getAllBySite(@PathVariable("site") String site) {
		return ResponseEntity.ok(menuService.findAllBySite(site));
	}
	
	@GetMapping("/{site}/{id}")
	public ResponseEntity<MenuEntity> getOneById(@PathVariable("site") String site, @PathVariable("id") String id) {
		return ResponseEntity.ok(menuService.findOneById(id));
	}
	
	@GetMapping("/{site}/category/{slug}")
	public ResponseEntity<List<MenuEntity>> getAllBySlug(@PathVariable("slug") String slug) {
		CategoryEntity amia_CategoryEntity = categoryService.findOneBySlug(slug);
		return ResponseEntity.ok(menuService.findAllByCategoryCode(amia_CategoryEntity));
	}
	
	@GetMapping("/combo")
	public ResponseEntity<List<MenuEntity>> getAllCombo() {
		CategoryEntity categoryEntity = categoryService.findOneBySlug("combo");
		return ResponseEntity.ok(menuService.findAllByCategoryCode(categoryEntity));
	}
	
	@PostMapping("/{site}")
	public ResponseEntity<MenuEntity> saveOne(@PathVariable("site") String site, @RequestBody MenuEntity menuEntity) throws Exception {
		
		List<PriceModel> prices = new ArrayList<PriceModel>();
		try {
			String URL = fileUtils.decoder(menuEntity.getImage(), "ImageAPI");
			menuEntity.setImage(URL);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		
		menuEntity.setSite(site);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm món " + menuEntity.getName() + " lúc " + ( df.parse(sf.format(Calendar.getInstance().getTime())) ) + " vào menu của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return ResponseEntity.ok(menuService.save(menuEntity));
	}
	
	@PostMapping("/combo")
	public ResponseEntity<MenuEntity> saveOne(@RequestBody MenuEntity menuEntity) throws Exception {
		
		List<PriceModel> prices = new ArrayList<PriceModel>();
		try {
			String URL = fileUtils.decoder(menuEntity.getImage(), "ImageAPI");
			menuEntity.setImage(URL);
		} catch (Exception e) {
			menuEntity.setImage("https://res.cloudinary.com/dbqzpdgpi/image/upload/v1596088950/MrVans/MrVans_Image_df9p6s.png");
			System.out.println("Exception: " + e);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã thêm combo " + menuEntity.getName() + " lúc " + ( df.parse(sf.format(Calendar.getInstance().getTime())) );
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return ResponseEntity.ok(menuService.save(menuEntity));
	}
	
	
	
	@PostMapping("/{site}/all")
	public ResponseEntity<String> saveAll(@PathVariable("site") String site, @RequestBody List<MenuEntity> menuEntity) {
		List<PriceModel> prices = new ArrayList<>();
		for (MenuEntity entity : menuEntity) {
			try {
				String URL = fileUtils.decoder(entity.getImage(), "ImageAPI");
				entity.setImage(URL);
				entity.setSite(site);
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
			menuService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PostMapping("/combo/all")
	public ResponseEntity<String> saveAllCombo(@RequestBody List<MenuEntity> menuEntity) {
		List<PriceModel> prices = new ArrayList<>();
		for (MenuEntity entity : menuEntity) {
			try {
				String URL = fileUtils.decoder(entity.getImage(), "ImageAPI");
				entity.setImage(URL);
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
			menuService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/{site}/{id}")
	public ResponseEntity<MenuEntity> saveOneById(@PathVariable("site") String site, @PathVariable String id, @RequestBody MenuEntity entity) throws Exception {

		MenuEntity newEntity = menuService.findOneById(id);
		newEntity = aMIA_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		newEntity.setSite(site);
		
		if( entity.getImage() != null ) {
			String URL = fileUtils.decoder(entity.getImage(), "ImageAPI");
			newEntity.setImage(URL);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin món " + entity.getName() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + " trong menu của " + site;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return ResponseEntity.ok(menuService.save(newEntity));
	}
	
	@PutMapping("/combo/{id}")
	public ResponseEntity<MenuEntity> saveOneByComboId(@PathVariable String id, @RequestBody MenuEntity entity) throws Exception {

		MenuEntity newEntity = menuService.findOneById(id);
		newEntity = aMIA_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		if( entity.getImage() != null ) {
			String URL = fileUtils.decoder(entity.getImage(), "ImageAPI");
			newEntity.setImage(URL);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin" + " đã sửa thông tin combo " + entity.getName() + " lúc " +( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) ;
		
		logEntity.setContent(content);
		logService.save(logEntity);
		
		return ResponseEntity.ok(menuService.save(newEntity));
	}
	
	@DeleteMapping("/combo/{id}")
	public void deleteOneByComboId(@PathVariable String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		MenuEntity menuEntity = menuService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa combo " + menuEntity.getName() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		menuService.delete(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		MenuEntity menuEntity = menuService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa món " + menuEntity.getName() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		menuService.delete(id);
	}
	
	@DeleteMapping("/{site}/{id}")
	public void deleteOneByIdAndSite(@PathVariable("site") String site, @PathVariable("id") String id, @RequestParam(required = false) String reason) throws Exception {
		String contentOfReason = ( reason != null ) ? ( " với lý do "  +  reason)  : " " ;
		
		MenuEntity menuEntity = menuService.findOneById(id);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-7"));
		
		LogEntity logEntity = new LogEntity();
		logEntity.setIcon("https://img.icons8.com/ios-filled/64/000000/information.png");
		String content = "Admin " + " đã xóa món " + menuEntity.getName() + " lúc " +  ( df.parse(sf.format(( Calendar.getInstance().getTime())) ) ) + contentOfReason;
		logEntity.setContent(content);
		logService.save(logEntity);
		
		menuService.delete(id);
	}
	
	@DeleteMapping("/{site}/all")
	public void deleteAllBySite(@PathVariable String site) {
		menuService.deleteAllBySite(site);
	}
	
	@DeleteMapping("/all")
	public void deleteAll() {
		menuService.deleteAll();
	}
	
	@GetMapping("/category/{site}/{slug}")
	public List<MenuEntity> getAllMenuByCategory(@PathVariable("site") String site, @PathVariable("slug") String slug) {
		CategoryEntity categoryEntity = categoryService.findOneBySlug(slug);
		List<MenuEntity> listEntity = menuService.findAllByCategoryCode(categoryEntity);
		List<MenuEntity> listEntityInSite = new ArrayList<MenuEntity>(); 
		for (MenuEntity entity : listEntity) {
			if(entity.getSite().equals(site)) {
				listEntityInSite.add(entity);
			}
		}
		
		return listEntityInSite;
	}
	
	

	
	
	
}
