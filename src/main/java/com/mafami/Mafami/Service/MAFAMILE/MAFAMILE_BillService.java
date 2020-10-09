package com.mafami.Mafami.Service.MAFAMILE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_BillEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_BillRepo;

@Service
public class MAFAMILE_BillService {

	@Autowired
	private MAFAMILE_BillRepo mAFAMILE_BillRepo;
	
	public MAFAMILE_BillEntity findOneById(String id) {	return mAFAMILE_BillRepo.findOneById(id); 	}
	
	public MAFAMILE_BillEntity findOneByProductID(String idProduct) {	return mAFAMILE_BillRepo.findOneByProductID(idProduct); 	}
	
	public MAFAMILE_BillEntity findOneByCustomerID(String idCustomer) {	return mAFAMILE_BillRepo.findOneByCustomerID(idCustomer); 	}

	public List<MAFAMILE_BillEntity> getAll() {		return mAFAMILE_BillRepo.findAll();		}

	public MAFAMILE_BillEntity save(MAFAMILE_BillEntity entity) {		return mAFAMILE_BillRepo.save(entity);		}
	
}
