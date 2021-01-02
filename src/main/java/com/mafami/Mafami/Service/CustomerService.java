package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.CustomerEntity;
import com.mafami.Mafami.Repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public CustomerEntity findOneById(String id) {
		return customerRepo.findOneById(id);
	}
	
	public List<CustomerEntity> findAllByPhone(String phone) {
		return customerRepo.findAllByPhone(phone);
	}

	public List<CustomerEntity> findAll() {
		return customerRepo.findAll();
	}
	
	public List<CustomerEntity> findAllByName(String name) {
		return customerRepo.findAllByName(name);
	}

	public Page<CustomerEntity> findAllByPage(int page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "name"));
		return customerRepo.findAll(pageable);
	}

	public CustomerEntity save(CustomerEntity entity) {
		return customerRepo.save(entity);
	}

	public void delete(String id) {
		customerRepo.delete(customerRepo.findOneById(id));
	}

	public void deleteAll() {
		customerRepo.deleteAll();
	}

}
