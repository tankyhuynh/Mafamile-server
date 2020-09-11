package com.mafami.Mafami.Convert;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.ProductEntity;

@Component
public class ProductConvert {

	public ProductEntity entity_to_entity(ProductEntity entity1) {
		ProductEntity entity2 = new ProductEntity();
		entity2.setName(entity1.getName());
		entity2.setCode(entity1.getCode());
		entity2.setPrice(entity2.getPrice());
		entity2.setId_Category(entity1.getId_Category());
		
		return entity2;
	}
	
}
