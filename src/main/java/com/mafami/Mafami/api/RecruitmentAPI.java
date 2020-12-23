package com.mafami.Mafami.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.RecruitmentEntity;
import com.mafami.Mafami.Service.RecruitmentService;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentAPI {

	@Autowired
	private RecruitmentService recruitmentService;

	@GetMapping
	public List<RecruitmentEntity> getAll() {
		return recruitmentService.getAll();
	}

	@PostMapping
	public RecruitmentEntity saveOneBySite(@PathVariable String site, @RequestBody RecruitmentEntity contactEntity) {
		return recruitmentService.save(contactEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteOneById(@PathVariable String id) {
		recruitmentService.delete(id);
	}

	@DeleteMapping("/all")
	public void deleteAll() {
		recruitmentService.deleteAll();
	}

}
