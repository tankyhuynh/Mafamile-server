package com.mafami.Mafami.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author root {1:18:15 PM}:
 * @version Creation time: Nov 8, 2020 1:18:15 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {

	@Id
	private String id;
	private String name;
	private String phone;

}
