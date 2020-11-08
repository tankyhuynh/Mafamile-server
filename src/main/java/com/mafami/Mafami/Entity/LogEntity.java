package com.mafami.Mafami.Entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author root {1:20:43 PM}:
 * @version Creation time: Nov 8, 2020 1:20:43 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Document(collection = "log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntity {

	@Id
	private String id;
	private Date time = Calendar.getInstance().getTime();
	private String entity;
	private String content;

}
