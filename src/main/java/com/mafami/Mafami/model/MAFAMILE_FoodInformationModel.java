package com.mafami.Mafami.model;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_MenuEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author root {11:16:38 AM}:
 * @version Creation time: Nov 11, 2020 11:16:38 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MAFAMILE_FoodInformationModel {

	private MAFAMILE_MenuEntity food;
	private int quantity;


	

}
