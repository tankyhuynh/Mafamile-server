package com.mafami.Mafami.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.Repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public CustomerEntity findOneById(String id_customer) {
		return customerRepo.findOneById(id_customer);
	} 
	
}
