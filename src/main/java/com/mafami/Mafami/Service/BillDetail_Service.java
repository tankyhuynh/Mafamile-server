package com.mafami.Mafami.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafami.Mafami.Entity.BillDetailEntity;
import com.mafami.Mafami.Entity.ProductEntity;
import com.mafami.Mafami.Repository.BillDetailRepo;

@Service
public class BillDetail_Service {

	@Autowired
	private BillDetailRepo billDetailRepo;
	
	public BillDetailEntity get(String id) {	return billDetailRepo.findOneById(id); 	}

	public List<BillDetailEntity> getAll() {		return billDetailRepo.findAll();		}

	public BillDetailEntity save(BillDetailEntity entity) {		return billDetailRepo.save(entity);		}
}
