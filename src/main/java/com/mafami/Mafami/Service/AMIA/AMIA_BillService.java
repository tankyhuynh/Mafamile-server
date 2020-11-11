package com.mafami.Mafami.Service.AMIA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.AMIA.AMIA_BillEntity;
import com.mafami.Mafami.Repository.AMIA.AMIA_BillRepo;

/**
* @author root {10:52:49 AM}:
 * @version Creation time: Nov 11, 2020 10:52:49 AM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Service
public class AMIA_BillService {

	@Autowired
	private AMIA_BillRepo amia_BillRepo;

	public AMIA_BillEntity getOneById(String id) {
		return amia_BillRepo.findOneById(id);
	}

	public List<AMIA_BillEntity> getAll() {
		return amia_BillRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
	}

	public AMIA_BillEntity save(AMIA_BillEntity entity) {
		return amia_BillRepo.save(entity);
	}

	public void delete(String id) {
		amia_BillRepo.deleteById(id);
	}

	public void deleteAll() {
		amia_BillRepo.deleteAll();
	}

}
