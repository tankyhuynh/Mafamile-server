package com.mafami.Mafami.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserEntity {

	@Id
	private String id;

	private String username;
	private String password;
	private String fullname;
	private String address;
	private String phone;
	private String role;
	private String email;
	private Date dateOfBirth;

	

}
