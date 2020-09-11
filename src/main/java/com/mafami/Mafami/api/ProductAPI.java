package com.mafami.Mafami.api;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Convert.ProductConvert;
import com.mafami.Mafami.Entity.ProductEntity;
import com.mafami.Mafami.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConvert productConvert;
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		return ResponseEntity.ok(productService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductEntity> getProduct(@PathVariable String id) {
		return ResponseEntity.ok(productService.getOne(id));
	}
	
	@PostMapping
	public ResponseEntity<ProductEntity> postProduct(@RequestBody ProductEntity productEntity) {
		return ResponseEntity.ok(productService.save(productEntity));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductEntity> putProduct(@PathVariable String id, @RequestBody ProductEntity entity) {
		ProductEntity newEntity = productService.getOne(id);
		newEntity = productConvert.entity_to_entity(entity);
		newEntity.setId(id);
		
		return ResponseEntity.ok(productService.save(newEntity));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable String id) {
		productService.delete(id);
	}

	
	
	
}
