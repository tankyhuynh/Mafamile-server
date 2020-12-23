package com.mafami.Mafami.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
import com.mafami.Mafami.model.RecruitTimeModel;

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
	public RecruitmentEntity saveOneBySite(@PathVariable String site, @RequestBody RecruitmentEntity billEntity) {
		Date dbDateStart = billEntity.getTime().getStartDate();
		Date dbDateEnd = billEntity.getTime().getEndDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
		
		RecruitTimeModel timeModel = new RecruitTimeModel();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sf.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));

		try {
			timeModel.setStartDate( df.parse(sf.format(dbDateStart)) );
			timeModel.setEndDate( df.parse( sf.format(dbDateEnd) ) );
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return recruitmentService.save(billEntity);
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
