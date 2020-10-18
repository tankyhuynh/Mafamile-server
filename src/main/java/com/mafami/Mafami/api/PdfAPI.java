package com.mafami.Mafami.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafami.Mafami.Entity.UserEntity;
import com.mafami.Mafami.Entity.MAFAMILE.MAFAMILE_PostEntity;
import com.mafami.Mafami.Service.UserService;
import com.mafami.Mafami.Service.MAFAMILE.MFAMILE_PostService;
import com.mafami.Mafami.Utils.PDFGeneratorUtils;

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
public class PdfAPI {

	@Autowired
	private MFAMILE_PostService mFAMILE_PostService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PDFGeneratorUtils pdfUtils;
	
	@Autowired
	private AbstractPDF<MAFAMILE_PostEntity> pdfUser;
	
	@GetMapping(value = "/pdf",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport() throws Exception {
		
		List<MAFAMILE_PostEntity> customers = (List<MAFAMILE_PostEntity>) mFAMILE_PostService.getAll();
//        ByteArrayInputStream bis = pdfUtils.customerPDFReport(customers);

        
        
//      String[] fieldName = {"id", "username", "password", "fullName", "roles", "token"};
        String[] fieldName = {"Id", "Title", "Content", "Images", "Username"};
        
        List<UserEntity> userEntities = userService.getAll();
        ByteArrayInputStream bis = pdfUser.customerPDFReport(fieldName, customers, MAFAMILE_PostEntity.class);
        
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
