package com.mafami.Mafami.Repository.AMIA;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.AMIA.AMIA_BillEntity;

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
public interface AMIA_BillRepo extends MongoRepository<AMIA_BillEntity, String>{

	AMIA_BillEntity findOneById(String id);
	
}
