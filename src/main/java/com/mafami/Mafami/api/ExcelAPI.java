package com.mafami.Mafami.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Service.BillService;
import com.mafami.Mafami.Utils.ExcelUtils_Bills;

@RestController
@RequestMapping("/api/excel")
public class ExcelAPI {

		@Autowired
		private BillService billService;
		
		@Autowired
		private ExcelUtils_Bills excelUtils_Bills;
	
	
		@GetMapping(value = "/bills")
		public void exportBills() throws Exception {
			
			List<BillEntity> bills = billService.getAll();
			String[] fieldName = {"ID", "Tên khách hàng", "Thông tin món", "Ngày tạo", "Ngày đặt", "Thông tin thêm", "Tổng"};
			
			excelUtils_Bills.export(fieldName, bills);
			
		}
	
}
