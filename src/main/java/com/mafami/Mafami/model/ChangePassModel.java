package com.mafami.Mafami.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassModel {

	private String oldPass;
	private String newPass;
	
}
