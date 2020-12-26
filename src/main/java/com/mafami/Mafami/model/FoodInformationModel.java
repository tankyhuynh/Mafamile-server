package com.mafami.Mafami.model;

import com.mafami.Mafami.Entity.MenuEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author root {11:11:28 AM}:
 * @version Creation time: Nov 11, 2020 11:11:28 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodInformationModel {

	private MenuEntity food;
	private String additionInformation;
	private int quantity;

}
