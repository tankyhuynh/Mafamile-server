package com.mafami.Mafami.Entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.RecruitTimeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("recruitments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentEntity {

	@Id
	private String id =(UUID.randomUUID()).toString().substring(0, 8);
	private String title;
	private String recruitmentInfo;
	private boolean status;
	private RecruitTimeModel time;
	private String site;

}
