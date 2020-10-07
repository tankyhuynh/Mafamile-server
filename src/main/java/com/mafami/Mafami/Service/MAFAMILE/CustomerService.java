package com.mafami.Mafami.Service.MAFAMILE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.MAFAMILE.CustomerEntity;
import com.mafami.Mafami.Repository.MAFAMILE.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public CustomerEntity findOneById(String id_customer) {
		return customerRepo.findOneById(id_customer);
	} 
	
}
