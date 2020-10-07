package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.ProductEntity;
import com.mafami.Mafami.Entity.MAFAMILE.UserEntity;
import com.mafami.Mafami.Repository.MAFAMILE.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public ProductEntity findOneById(String idProduct) {	return productRepo.findOneById(idProduct); 	}
	
	public ProductEntity findOneByCategoryID(String idCategory) {	return productRepo.findOneByCategoryID(idCategory); 	}
	
	public ProductEntity findOneByName(String name) {	return productRepo.findOneByName(name); 	}

	public List<ProductEntity> getAll() {		return productRepo.findAll();		}

	public ProductEntity save(ProductEntity entity) {		return productRepo.save(entity);		}
	
	public void delete(String id) {		productRepo.delete(productRepo.findOneById(id));		}
	
	
	
}
