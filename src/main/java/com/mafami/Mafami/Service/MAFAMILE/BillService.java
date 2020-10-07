package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.BillEntity;
import com.mafami.Mafami.Repository.MAFAMILE.BillRepo;

@Service
public class BillService {

	@Autowired
	private BillRepo billRepo;
	
	public BillEntity findOneById(String id) {	return billRepo.findOneById(id); 	}
	
	public BillEntity findOneByProductID(String idProduct) {	return billRepo.findOneByProductID(idProduct); 	}
	
	public BillEntity findOneByCustomerID(String idCustomer) {	return billRepo.findOneByCustomerID(idCustomer); 	}

	public List<BillEntity> getAll() {		return billRepo.findAll();		}

	public BillEntity save(BillEntity entity) {		return billRepo.save(entity);		}
	
}
