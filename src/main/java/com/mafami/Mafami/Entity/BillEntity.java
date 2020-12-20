package com.mafami.Mafami.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mafami.Mafami.model.CustomerModel;
import com.mafami.Mafami.model.FoodInformationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author root {10:45:58 AM}:
 * @version Creation time: Nov 11, 2020 10:45:58 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Document(collection = "bills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillEntity {

	@Id
	private String id;

	private CustomerModel customerInformation;
	private List<FoodInformationModel> foodInformation;
	private Date createdDate = Calendar.getInstance().getTime();
	private Date orderDate;
	private String additionInformation;
	private boolean isConfirmed;
	private double total;
	private String site;


}
