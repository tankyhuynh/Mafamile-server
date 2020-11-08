package com.mafami.Mafami.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.ContactEntity;

/**
* @author root {1:53:45 PM}:
 * @version Creation time: Nov 8, 2020 1:53:45 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Repository
public interface ContactRepo extends MongoRepository<ContactEntity, String> {

	ContactEntity findOneById(String id);
	List<ContactEntity> findAllBySite(String site);
	
}
