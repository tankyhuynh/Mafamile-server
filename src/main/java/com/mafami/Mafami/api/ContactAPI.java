package com.mafami.Mafami.api;

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

import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Service.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactAPI {

	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public List<ContactEntity> getAll() {
		return contactService.getAll();
	}

	@GetMapping("/{site}")
	public List<ContactEntity> getAllBySite(@PathVariable String site) {
		return contactService.getAllBySite(site);
	}

	@GetMapping("/{site}/{id}")
	public ResponseEntity<ContactEntity> getOneById( @PathVariable("id") String id) {
		ContactEntity logEntity = contactService.findOneById(id);
		if (logEntity != null) {
			return ResponseEntity.ok(contactService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping("/{site}")
	public ContactEntity saveOneBySite(@PathVariable String site, @RequestBody ContactEntity contactEntity) {
		contactEntity.setSite(site);
		return contactService.save(contactEntity);
	}

	@PutMapping("/{site}/{id}")
	public ResponseEntity<ContactEntity> saveOneById( @PathVariable("site") String site ,@PathVariable("id") String id, @RequestBody ContactEntity newEntity) {
		ContactEntity oldEntity = contactService.findOneById(id);
		newEntity.setId(id);
		newEntity.setSite(site);
		if (oldEntity != null)
			return ResponseEntity.ok(contactService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
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
