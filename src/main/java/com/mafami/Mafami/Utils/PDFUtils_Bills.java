package com.mafami.Mafami.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mafami.Mafami.Entity.BillEntity;
import com.mafami.Mafami.model.FoodInformationModel;

/**
* @author root {4:49:19 PM}:
 * @version Creation time: Oct 7, 2020 4:49:19 PM
 * Class Description
*/
/**
 * @author tankyhuynh
 *
 */

@Component
public class PDFUtils_Bills<T> {

	public static File fontFile = new File("/vuArial.ttf");

	
	public static <T> ByteArrayInputStream toPDF(String[] fieldName, List<BillEntity> billEntities, String site) throws Exception {
		Document document = new Document();
		document.setPageSize(PageSize.A2);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();
			
			// Add Text to PDF file ->
			BaseFont bf = BaseFont.createFont(fontFile.getPath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font font = new Font(bf,15);
	        Font titleFont = new Font(bf,25);
	        titleFont.setColor(BaseColor.RED);
	        titleFont.setStyle(titleFont.BOLD);
			Paragraph para = new Paragraph("Thống kê hóa đơn trên " + site, titleFont);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(fieldName.length);
			table.setWidthPercentage(100);
			// Add PDF Table Header ->
			Stream.of(fieldName).forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headerFont = new Font(bf,15);
				headerFont.setStyle(headerFont.BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPadding(5);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setPhrase(new Phrase(headerTitle, headerFont));
				table.addCell(header);
			});


			
			
			
			for (BillEntity bill : billEntities) {
				
				
				PdfPCell idCell = new PdfPCell(new Phrase(  bill.getId() , font));
				idCell.setPaddingLeft(6);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				idCell.setBorderWidth(2);
				table.addCell(idCell);
				
				
				String customerInformation = (bill.getCustomerInformation().getName() != null) ? (bill.getCustomerInformation().getName()) : " ";
				PdfPCell customerCell = new PdfPCell(new Phrase( customerInformation , font));
				customerCell.setPaddingLeft(6);
				customerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				customerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				customerCell.setBorderWidth(2);
				table.addCell(customerCell);
				
		
				String foods = "";
				for (FoodInformationModel item : bill.getFoodInformation()) {
					foods += item.getFood().getName() + " - " + item.getQuantity() + "\n\n";
				}
	
				PdfPCell foodCell = new PdfPCell(new Phrase( foods , font));
				foodCell.setPaddingLeft(6);
				foodCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				foodCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				foodCell.setBorderWidth(2);
				table.addCell(foodCell);

				
				PdfPCell createdDateCell = new PdfPCell(new Phrase(String.valueOf(bill.getCreatedDate()), font));
				createdDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				createdDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				createdDateCell.setPaddingRight(6);
				createdDateCell.setBorderWidth(2);
				table.addCell(createdDateCell);

				
				String orderDate = bill.getOrderDate().toString();
				PdfPCell orderDateCell = new PdfPCell(new Phrase( orderDate , font));
				orderDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				orderDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				orderDateCell.setBorderWidth(2);
				orderDateCell.setPaddingRight(6);
				table.addCell(orderDateCell);
				
				
				
			
				PdfPCell additionInformationCell = new PdfPCell(new Phrase(String.valueOf( bill.getAdditionInformation() ), font));
				additionInformationCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				additionInformationCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				additionInformationCell.setBorderWidth(2);
				additionInformationCell.setPaddingRight(6);
				table.addCell(additionInformationCell);

				
				String total =String.valueOf(bill.getTotal());
				PdfPCell totalCell = new PdfPCell(new Phrase( total , font ));
				totalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalCell.setBorderWidth(2);
				totalCell.setPaddingRight(6);
				table.addCell(totalCell);

				
//				PdfPCell siteCell = new PdfPCell(new Phrase(String.valueOf( bill.getSite() ), font));
//				siteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//				siteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				siteCell.setBorderWidth(2);
//				siteCell.setPaddingRight(6);
//				table.addCell(siteCell);
				
				//"id", "customerInformation", "foodInformation", "createdDate", "orderDate", "additionInformation", "total", "site"

			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			System.out.println(e.toString());
		}
		

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	public static void analyze(Object obj){
	    ReflectionUtils.doWithFields(obj.getClass(), field -> {

	        System.out.print("Field name: " + field.getName());
	        field.setAccessible(true);
	        System.out.println("\tField value: "+ field.get(obj));

	    });
	}
	
	
	
	 public static PdfPCell newCell(String text, Font font) {
		    PdfPCell pdfCell = new PdfPCell();
		    PdfPCell cell = new PdfPCell();

		    if (text == null) {
		      // create a blank cell
		      cell = new PdfPCell(new Phrase(" ", font));
		    }
		    else {
		      cell = new PdfPCell(new Phrase(text, font));
		    }

		    pdfCell = new PdfPCell(cell);

		    return pdfCell;
		  }

	public static void main(String[] args) throws DocumentException, Exception {

		
	}
	
}
