package com.mafami.Mafami.Convert.AMIA;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.AMIA.AMIA_ProductEntity;

@Component
public class AMIA_ProductConvert {

	public AMIA_ProductEntity entity_to_entity(AMIA_ProductEntity entity1) {
		AMIA_ProductEntity entity2 = new AMIA_ProductEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity2.getPrice());
		entity2.setCategory(entity1.getCategory());
		entity2.setImage(entity1.getImage());

		return entity2;
	}

}
