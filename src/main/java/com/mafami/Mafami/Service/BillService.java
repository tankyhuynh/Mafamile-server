package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.BillDetailEntity;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Repository.BillRepo;

@Service
public class BillService {

	@Autowired
	private BillRepo billRepo;
	
	public BillEntity get(String id) {	return billRepo.findOneById(id); 	}

	public List<BillEntity> getAll() {		return billRepo.findAll();		}

	public BillEntity save(BillEntity entity) {		return billRepo.save(entity);		}
	
}
