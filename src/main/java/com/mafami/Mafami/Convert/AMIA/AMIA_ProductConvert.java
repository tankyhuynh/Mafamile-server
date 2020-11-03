package com.mafami.Mafami.Convert.AMIA;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.AMIA.AMIA_MenuEntity;

@Component
public class AMIA_ProductConvert {

	public AMIA_MenuEntity entity_to_entity(AMIA_MenuEntity entity1) {
		AMIA_MenuEntity entity2 = new AMIA_MenuEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity2.getPrice());
		entity2.setCategory(entity1.getCategory());
		entity2.setImage(entity1.getImage());
		entity2.setSite(entity1.getSite());

		return entity2;
	}

}
