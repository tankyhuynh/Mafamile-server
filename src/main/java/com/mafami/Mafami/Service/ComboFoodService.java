package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ComboFoodEntity;
import com.mafami.Mafami.Repository.ComboFoodRepo;

@Service
public class ComboFoodService {

	@Autowired
	private ComboFoodRepo comboFoodRepo;

	public ComboFoodEntity findOneById(String id) {
		return comboFoodRepo.findOneById(id);
	}

	public List<ComboFoodEntity> findAll() {
		return comboFoodRepo.findAll();
	}

	public Page<ComboFoodEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "time"));
		return comboFoodRepo.findAll(pageable);
	}

	public ComboFoodEntity save(ComboFoodEntity entity) {
		return comboFoodRepo.save(entity);
	}

	public void delete(String id) {
		comboFoodRepo.delete(comboFoodRepo.findOneById(id));
	}

	public void deleteAll() {
		comboFoodRepo.deleteAll();
	}

}
