package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Entity.ContactEntity;
import com.mafami.Mafami.Repository.BillRepo;

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
public class BillService {

	@Autowired
	private BillRepo billRepo;

	public BillEntity getOneById(String id) {
		return billRepo.findOneById(id);
	}

	public List<BillEntity> getAll() {
		return billRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
	}
	
	public List<BillEntity> getAllBySite(String site) {
		return billRepo.findAllBySite(site, Sort.by(Sort.Direction.DESC, "createdDate"));
	}

	public BillEntity save(BillEntity entity) {
		return billRepo.save(entity);
	}

	public void delete(String id) {
		billRepo.deleteById(id);
	}
	
	public void deleteAllBySite(String site) {
		List<BillEntity> listBills = billRepo.findAllBySite(site, Sort.by(Sort.Direction.DESC, "createdDate"));
		billRepo.deleteAll(listBills);
	}

	public void deleteAll() {
		billRepo.deleteAll();
	}

}
