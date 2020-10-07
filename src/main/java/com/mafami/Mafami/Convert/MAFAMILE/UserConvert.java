package com.mafami.Mafami.Convert.MAFAMILE;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.MAFAMILE.UserEntity;

@Component
public class UserConvert {

	public UserEntity Entity_To_Entity(UserEntity entity1) {
		UserEntity entity2 = new UserEntity();
		entity2.setId(entity1.getId());
		entity2.setUsername(entity1.getUsername());
		entity2.setPassword(entity1.getPassword());
		entity2.setFullname((entity1.getFullname()));
		entity2.setRoles(entity1.getRoles());
		
		return entity2;
	}
	
}
