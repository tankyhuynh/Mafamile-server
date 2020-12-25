package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Repository.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public CategoryEntity findOneById(String id) {
		return categoryRepo.findOneById(id);
	}
	
	public CategoryEntity findOneBySlug(String slug) {
		return categoryRepo.findOneBySlug(slug);
	}

	public List<CategoryEntity> findAll() {
		return categoryRepo.findAll();
	}
	
	public List<CategoryEntity> findAllBySite(String site) {
		return categoryRepo.findAllBySite(site);
	}
	
	public Page<CategoryEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "name"));
		return categoryRepo.findAll(pageable);
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
