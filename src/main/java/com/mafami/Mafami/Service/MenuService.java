package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CategoryEntity;
import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.Repository.MenuRepo;

@Service
public class MenuService {

	@Autowired
	private MenuRepo menuRepo;

	public MenuEntity findOneById(String idProduct) {
		return menuRepo.findById(idProduct).orElse(new MenuEntity());
	}

	public List<MenuEntity> findAllByCategoryCode(CategoryEntity categoryCode) {
		return menuRepo.findAllByCategory(categoryCode);
	}

	public List<MenuEntity> findAllBySite(String site) {
		return menuRepo.findAllBySite(site);
	}

	public List<MenuEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10);
		return menuRepo.findAll(pageable).getContent();
	}

	public MenuEntity findOneByName(String name) {
		return menuRepo.findOneByName(name);
	}

	public List<MenuEntity> getAll() {
		return menuRepo.findAll();
	}

	public MenuEntity save(MenuEntity entity) {
		return menuRepo.save(entity);
	}

	public void delete(String id) {
		menuRepo.deleteById(id);
	}

	public void deleteAllBySite(String site) {
		List<MenuEntity> listMenu = menuRepo.findAllBySite(site);
		menuRepo.deleteAll(listMenu);
	}

	public void deleteAll() {
		menuRepo.deleteAll();
	}

}
