package com.mafami.Mafami.Repository;

import java.util.List;

/**
* @author root {1:24:28 PM}:
 * @version Creation time: Nov 8, 2020 1:24:28 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mafami.Mafami.Entity.LogEntity;

@Repository
public interface LogRepo extends MongoRepository<LogEntity, String> {

	LogEntity findOneById(String id);

}
