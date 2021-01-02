package com.mafami.Mafami.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.Service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public List<CustomerEntity> getAll() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerEntity> getOneById(@PathVariable String id) {
		CustomerEntity customerEntity = customerService.findOneById(id);
		if (customerEntity != null) {
			return ResponseEntity.ok(customerService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public CustomerEntity saveOne(@RequestBody CustomerEntity userEntity) {
		return customerService.save(userEntity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerEntity> saveOneById(@PathVariable String id,
			@RequestBody(required = false) CustomerEntity newEntity) {
		CustomerEntity oldEntity = customerService.findOneById(id);
		newEntity.setId(id);
		if (oldEntity != null)
			return ResponseEntity.ok(customerService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		customerService.delete(id);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		customerService.deleteAll();
	}

}
