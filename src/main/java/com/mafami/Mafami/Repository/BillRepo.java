package com.mafami.Mafami.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.BillEntity;

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
	List<BillEntity> findAllByCreatedDateBetween(Date createdDate1, Date createdDate2);
	
}
