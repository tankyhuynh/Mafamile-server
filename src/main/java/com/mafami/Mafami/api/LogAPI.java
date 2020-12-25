package com.mafami.Mafami.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
import com.mafami.Mafami.Service.LogService;

/**
* @author root {1:29:06 PM}:
 * @version Creation time: Nov 8, 2020 1:29:06 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@RestController
@RequestMapping("/api/log")
public class LogAPI {

	@Autowired
	private LogService logService;

	@GetMapping
	public Page<LogEntity> getAll() {
		return logService.getAll();
	}
	
	@GetMapping("/page/{numberOfPage}")
	public Page<LogEntity> getAllByNumberOfPage(@PathVariable("numberOfPage") int numberOfPage) {
		return logService.getAllByPage(numberOfPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LogEntity> getOneById(@PathVariable String id) {
		LogEntity logEntity = logService.findOneById(id);
		if (logEntity != null) {
			return ResponseEntity.ok(logService.findOneById(id));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public LogEntity saveOne(@RequestBody LogEntity userEntity) {
		return logService.save(userEntity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LogEntity> saveOneById(@PathVariable String id, @RequestBody(required = false) LogEntity newEntity) {
		LogEntity oldEntity = logService.findOneById(id);
		newEntity.setId(id);
		if (oldEntity != null)
			return ResponseEntity.ok(logService.save(newEntity));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		logService.delete(id);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		logService.deleteAll();
	}

}
