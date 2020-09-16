package com.mafami.Mafami.Convert;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.ProductEntity;

@Component
public class ProductConvert {

	public ProductEntity entity_to_entity(ProductEntity entity1) {
		ProductEntity entity2 = new ProductEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity2.getPrice());
		entity2.setIdcategory(entity1.getIdcategory());
		entity2.setImage(entity1.getImage());
		
		return entity2;
	}
	
}
