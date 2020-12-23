package com.mafami.Mafami.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitTimeModel {

	private Date startDate;
	private Date endDate;

}
