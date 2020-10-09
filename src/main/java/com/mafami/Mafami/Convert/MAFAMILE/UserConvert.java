package com.mafami.Mafami.Convert.MAFAMILE;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_UserEntity;

@Component
public class UserConvert {

	public MAFAMILE_UserEntity Entity_To_Entity(MAFAMILE_UserEntity entity1) {
		MAFAMILE_UserEntity entity2 = new MAFAMILE_UserEntity();
		entity2.setId(entity1.getId());
		entity2.setUsername(entity1.getUsername());
		entity2.setPassword(entity1.getPassword());
		entity2.setFullname((entity1.getFullname()));
		entity2.setRoles(entity1.getRoles());
		
		return entity2;
	}
	
}
