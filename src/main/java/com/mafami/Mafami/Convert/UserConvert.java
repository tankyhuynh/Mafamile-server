package com.mafami.Mafami.Convert;

import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.UserEntity;

@Component
public class UserConvert {

	public UserEntity Entity_To_Entity(UserEntity entity1) {
		UserEntity entity2 = new UserEntity();
		entity2.setId(entity1.getId());
		entity2.setUserName(entity1.getUserName());
		entity2.setPassword(entity1.getPassword());
		entity2.setFullName(entity1.getFullName());
		entity2.setRoles(entity1.getRoles());
		
		return entity2;
	}
	
}
