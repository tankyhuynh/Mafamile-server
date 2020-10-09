package com.mafami.Mafami.Service.MAFAMILE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_CustomerEntity;
import com.mafami.Mafami.Repository.MAFAMILE.MAFAMILE_CustomerRepo;

@Service
public class MAFAMILE_CustomerService {

	@Autowired
	private MAFAMILE_CustomerRepo mAFAMILE_CustomerRepo;
	
	public MAFAMILE_CustomerEntity findOneById(String id_customer) {
		return mAFAMILE_CustomerRepo.findOneById(id_customer);
	} 
	
}
