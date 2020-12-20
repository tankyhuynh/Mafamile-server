package com.mafami.Mafami.Convert.AMIA;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.MenuEntity;

@Component
public class AMIA_ProductConvert {

	public MenuEntity entity_to_entity(MenuEntity entity1) {
		MenuEntity entity2 = new MenuEntity();
		entity2.setName(entity1.getName());
		entity2.setPrice(entity1.getPrice());
		entity2.setCategory(entity1.getCategory());
		entity2.setImage(entity1.getImage());
		entity2.setSite(entity1.getSite());

		return entity2;
	}

}
