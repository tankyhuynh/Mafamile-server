package com.mafami.Mafami.Convert.MAFAMILE;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;

@Component
public class MAFAMILE_ProductConvert {

	public MAFAMILE_MenuEntity entity_to_entity(MAFAMILE_MenuEntity entity1) {
		MAFAMILE_MenuEntity entity2 = new MAFAMILE_MenuEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity1.getPrice());
		entity2.setCategory(entity1.getCategory());
		entity2.setImage(entity1.getImage());
		entity2.setSite(entity1.getSite());

		return entity2;
	}

}
