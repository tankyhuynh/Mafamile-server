package com.mafami.Mafami.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.Repository.BillRepo;
import com.mafami.Mafami.model.CustomerModel;

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
		return billRepo.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
	}

	public Page<BillEntity> getAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "orderDate"));
		return billRepo.findAll(pageable);
	}

	public List<BillEntity> getAllBySite(String site) {
		return billRepo.findAllBySite(site, Sort.by(Sort.Direction.DESC, "orderDate"));
	}

	public List<BillEntity> getAllByOrderDate(Date orderDateStart) {
		return billRepo.findAllByOrderDate(orderDateStart);
	}

	public List<BillEntity> getAllByOrderDateBetween(Date orderDate1, Date orderDate2) {
		return billRepo.findAllByOrderDateBetween(orderDate1, orderDate2);
	}
	
	public List<BillEntity> getAllByCustomer(CustomerEntity customer) {
		return billRepo.findAllByCustomerInformation(customer);
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
