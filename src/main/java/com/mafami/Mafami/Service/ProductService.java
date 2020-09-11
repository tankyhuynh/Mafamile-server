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
	
	public ProductEntity getOne(String id) {	return productRepo.findOneById(id); 	}

	public List<ProductEntity> getAll() {		return productRepo.findAll();		}

	public ProductEntity save(ProductEntity entity) {		return productRepo.save(entity);		}
	
	public void delete(String id) {		productRepo.delete(productRepo.findOneById(id));		}
	
	
	
}
