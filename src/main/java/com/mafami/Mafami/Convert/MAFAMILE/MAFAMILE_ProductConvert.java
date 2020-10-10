package com.mafami.Mafami.Convert.MAFAMILE;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_ProductEntity;

@Component
public class MAFAMILE_ProductConvert {

	public MAFAMILE_ProductEntity entity_to_entity(MAFAMILE_ProductEntity entity1) {
		MAFAMILE_ProductEntity entity2 = new MAFAMILE_ProductEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity2.getPrice());
		entity2.setCategoryCode(entity1.getCategoryCode());
		entity2.setImage(entity1.getImage());

		return entity2;
	}

}
