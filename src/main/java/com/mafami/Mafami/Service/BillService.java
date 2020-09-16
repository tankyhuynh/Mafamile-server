package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Repository.BillRepo;

@Service
public class BillService {

	@Autowired
	private BillRepo billRepo;
	
	public BillEntity findOneById(String id) {	return billRepo.findOneById(id); 	}
	
	public BillEntity findOneByIdproduct(String idProduct) {	return billRepo.findOneByIdproduct(idProduct); 	}
	
	public BillEntity findOneByIdcustomer(String idCustomer) {	return billRepo.findOneByIdcustomer(idCustomer); 	}

	public List<BillEntity> getAll() {		return billRepo.findAll();		}

	public BillEntity save(BillEntity entity) {		return billRepo.save(entity);		}
	
}
