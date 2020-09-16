package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.ProductEntity;
import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public ProductEntity findOneById(String idProduct) {	return productRepo.findOneById(idProduct); 	}
	
	public ProductEntity findOneByIdcategory(String idCategory) {	return productRepo.findOneByIdcategory(idCategory); 	}
	
	public ProductEntity findOneByName(String name) {	return productRepo.findOneByName(name); 	}

	public List<ProductEntity> getAll() {		return productRepo.findAll();		}

	public ProductEntity save(ProductEntity entity) {		return productRepo.save(entity);		}
	
	public void delete(String id) {		productRepo.delete(productRepo.findOneById(id));		}
	
	
	
}
