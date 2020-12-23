package com.mafami.Mafami.Utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.mafami.Mafami.Entity.BillEntity;

@Component
public class ExcelUtils_Bills {
	
	private static String FILE_NAME = "Thong_ke_hoa_don_theo_ngay.xlsx";

	public static byte[] export(String[] fieldName, List<BillEntity> billEntities) throws Exception {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Hóa đơn");
		

		Row header = sheet.createRow(0);
		

		CellStyle headerStyle = workbook.createCellStyle();
//		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);

		//// Write header
//		Cell headerCell = header.createCell(0);
//		headerCell.setCellValue("Name");
//		headerCell.setCellStyle(headerStyle);
		
		// Write value
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		
		for (int i = 0; i < fieldName.length; i++) {
			sheet.setColumnWidth(i, 10000);
			
			Cell headerCell = header.createCell(i);
			headerCell.setCellValue(fieldName[i]);
			headerCell.setCellStyle(headerStyle);
		
		}


		int i = 2;
		for (BillEntity bill : billEntities) {
			
				Row row = sheet.createRow(i);
			
				Cell cell = row.createCell(0);
				cell.setCellValue(bill.getId());
				cell.setCellStyle(style);
				String customerInformation = "Chưa điền thông tin cá nhân";
				try {
					customerInformation = bill.getCustomerInformation().getName();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = row.createCell(1);
				cell.setCellValue( customerInformation );
				cell.setCellStyle(style);
				
				String foods = "";
				for (int j = 0; j < bill.getFoodInformation().size(); j++) {
					if ( (j != (bill.getFoodInformation().size() -1) )) {
						foods += bill.getFoodInformation().get(j).getFood().getName() + " - " + bill.getFoodInformation().get(j).getQuantity() + "\n\n";
					}else foods += bill.getFoodInformation().get(j).getFood().getName() + " - " + bill.getFoodInformation().get(j).getQuantity() + "\n";
				}
				
				cell = row.createCell(2);
				cell.setCellValue(foods);
				cell.setCellStyle(style);
				
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				df.setTimeZone(TimeZone.getTimeZone("Etc/GMT0"));
				
				Date createdDate = bill.getCreatedDate();        
				LocalDate localCreatedDate = createdDate.toInstant().atZone(ZoneId.of("Etc/GMT-7")).toLocalDate();
				cell = row.createCell(3);
				cell.setCellValue( df.format(createdDate) );
				cell.setCellStyle(style);
				
				Date dbDate = bill.getOrderDate();                      
				
				
				// Use Madrid's time zone to format the date in
				
				LocalDate localDateEntity = dbDate.toInstant().atZone(ZoneId.of("Etc/GMT-7")).toLocalDate();
				
				System.out.println("Orderdate: " + bill.getOrderDate());
				System.out.println("Orderdate format: " + df.format(dbDate) );
				System.out.println();
				cell = row.createCell(4);
				cell.setCellValue( df.format(dbDate) );
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(bill.getAdditionInformation());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue( bill.getTotal() + " VND");
				cell.setCellStyle(style);
			
				i++;
		}
	

		FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.write(outputStream);
        workbook.close();
  
		System.out.println("Success");
		
		return bos.toByteArray();
	}
	
	
	
	
	

}
