package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public CategoryEntity getOneById(String id) {
		return categoryRepo.findOneById(id);
	}
	
	public CategoryEntity getOneBySlug(String slug) {
		return categoryRepo.findOneBySlug(slug);
	}

	public List<CategoryEntity> getAll() {
		return categoryRepo.findAll();
	}
	
	public List<CategoryEntity> getAllBySite(String site) {
		return categoryRepo.findAllBySite(site);
	}
	

	public CategoryEntity save(CategoryEntity entity) {
		return categoryRepo.save(entity);
	}
	
	public void delete(String id) {
		categoryRepo.deleteById(id);
	}
	
	public void deleteAllBySite(String site) {
		List<CategoryEntity> listContacts = categoryRepo.findAllBySite(site);
		categoryRepo.deleteAll(listContacts);
	}
	
	public void deleteAll() {
		categoryRepo.deleteAll();
	}

}
