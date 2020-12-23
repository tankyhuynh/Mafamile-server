package com.mafami.Mafami.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("recruitment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentEntity {

	@Id
	private String id;
	private String title;
	private String recruitmentInfo;
	private boolean status;
	private Date time;
	
}
