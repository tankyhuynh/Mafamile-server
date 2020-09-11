package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public CategoryEntity get(String id) {	return categoryRepo.findOneByName(id); 	}

	public List<CategoryEntity> getAll() {		return categoryRepo.findAll();		}

	public CategoryEntity save(CategoryEntity entity) {		return categoryRepo.save(entity);		}
	
}
