package com.mafami.Mafami.api;

import java.util.ArrayList;
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

import com.mafami.Mafami.Convert.AMIA.AMIA_ProductConvert;
import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.Service.CategoryService;
import com.mafami.Mafami.Service.MenuService;
import com.mafami.Mafami.Utils.FileUtils;
import com.mafami.Mafami.model.PriceModel;


@RestController
@RequestMapping("/api/menu")
public class MenuAPI {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AMIA_ProductConvert aMIA_ProductConvert;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private CategoryService amia_CategoryService;
	
	@GetMapping
	public ResponseEntity<List<MenuEntity>> getAll() {
		return ResponseEntity.ok(menuService.getAll());
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
		CategoryEntity amia_CategoryEntity = amia_CategoryService.getOneBySlug(slug);
		return ResponseEntity.ok(menuService.findAllByCategoryCode(amia_CategoryEntity));
	}
	
	@PostMapping("/{site}")
	public ResponseEntity<MenuEntity> saveOne(@PathVariable("site") String site, @RequestBody MenuEntity menuEntity) {
		
		String URL = fileUtils.decoder(menuEntity.getImage(), "ImageAPI");
		menuEntity.setImage(URL);
		menuEntity.setSite(site);
		return ResponseEntity.ok(menuService.save(menuEntity));
	}
	
	@PostMapping("/{site}/all")
	public ResponseEntity<String> saveAll(@PathVariable("site") String site, @RequestBody List<MenuEntity> menuEntity) {
		List<PriceModel> prices = new ArrayList<>();
		for (MenuEntity entity : menuEntity) {
			for (PriceModel price : entity.getPrice()) {
				if( price.getSize() != null ) {
					prices.add(price);
				}
				else {
					price.setSize("M");
					prices.add(price);
				}
			}
			entity.setPrice(prices);
			entity.setSite(site);
			menuService.save(entity);
		}
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/{site}/{id}")
	public ResponseEntity<MenuEntity> saveOneById(@PathVariable("site") String site, @PathVariable String id, @RequestBody MenuEntity entity) {
		MenuEntity newEntity = menuService.findOneById(id);
		newEntity = aMIA_ProductConvert.entity_to_entity(entity);
		newEntity.setId(id);
		newEntity.setSite(site);
		
		return ResponseEntity.ok(menuService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
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

	
	
	
}
