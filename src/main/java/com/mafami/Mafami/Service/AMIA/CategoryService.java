package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.CategoryEntity;
import com.mafami.Mafami.Repository.AMIA.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public CategoryEntity findOneById(String id) {
		return categoryRepo.findOneById(id);
	}

	public List<CategoryEntity> getAll() {
		return categoryRepo.findAll();
	}

	public CategoryEntity save(CategoryEntity entity) {
		return categoryRepo.save(entity);
	}

}
