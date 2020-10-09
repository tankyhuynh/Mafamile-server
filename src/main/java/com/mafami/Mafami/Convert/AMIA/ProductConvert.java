package com.mafami.Mafami.Convert.AMIA;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.AMIA.ProductEntity;



@Component
public class ProductConvert {

	public ProductEntity entity_to_entity(ProductEntity entity1) {
		ProductEntity entity2 = new ProductEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity2.getPrice());
		entity2.setCategoryID(entity1.getCategoryID());
		entity2.setImage(entity1.getImage());
		
		return entity2;
	}
	
}
