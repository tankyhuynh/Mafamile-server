package com.mafami.Mafami.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.model.CustomerModel;

/**
* @author root {10:51:48 AM}:
 * @version Creation time: Nov 11, 2020 10:51:48 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@Repository
public interface BillRepo extends MongoRepository<BillEntity, String>{

	BillEntity findOneById(String id);
	List<BillEntity> findAllBySite(String site, Sort sort);
	
	List<BillEntity> findAllByOrderDate(Date orderDateStart);
	List<BillEntity> findAllByOrderDateBetween(Date orderDate1, Date orderDate2);
	
	// id	sdt	ten
	List<BillEntity> findAllByCustomerInformation(CustomerEntity customer);
	
}
