package com.mafami.Mafami.api;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.Entity.PostEntity;
import com.mafami.Mafami.Service.BillService;
import com.mafami.Mafami.Service.PostService;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.Utils.AbstractPDF;
import com.mafami.Mafami.Utils.PDFUtils_Bills;
import com.mafami.Mafami.Utils.PDFUtils_Posts;

/**
* @author root {4:14:19 PM}:
 * @version Creation time: Oct 7, 2020 4:14:19 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */
@RestController
@RequestMapping("/api/pdf")
public class PdfAPI {

	@Autowired
	private PostService postService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PDFUtils_Posts<PostEntity> pdfPosts;
	
	@Autowired
	private PDFUtils_Bills<BillEntity> pdfBills;
	
	@Autowired
	private AbstractPDF<PostEntity> pdfUser;
	
	@GetMapping(value = "/post/{site}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfPosts(@PathVariable String site) throws Exception {

		List<PostEntity> posts = (List<PostEntity>) postService.findAllBySite(site);
        String[] fieldName = {"ID", "Tiêu đề", "Nội dung", "Thời gian", "Tác giả", "Đường dẫn hình ảnh", "Preview"};
        ByteArrayInputStream bis = pdfPosts.toPDF(fieldName, posts, site);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        
        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
    }
	
	
	
	@GetMapping(value = "/bill/{site}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfBills(@PathVariable String site) throws Exception {

		//"id", "customerInformation", "foodInformation", "createdDate", "orderDate", "additionInformation", "total", "site"
        List<BillEntity> bills = (List<BillEntity>) billService.getAllBySite(site);
        String[] fieldName = {"ID", "Tên khách hàng", "Thông tin món", "Ngày tạo", "Ngày đặt", "Thông tin thêm", "Tổng"};
        ByteArrayInputStream bis = pdfBills.toPDF(fieldName, bills, site);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        
        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
    }
	
	
	
	
	
	
	
}
