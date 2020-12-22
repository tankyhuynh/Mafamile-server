package com.mafami.Mafami.Utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	
	private static String FILE_NAME = "tmp.xlsx";

	public static void export(String[] fieldName, List<BillEntity> billEntities) throws Exception {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Persons");
		

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
				String customerInformation = "No Name";
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
				
				
				cell = row.createCell(3);
				cell.setCellValue( SimpleDateFormat.getInstance().format(bill.getCreatedDate()) );
				cell.setCellStyle(style);
				
				cell = row.createCell(4);
				cell.setCellValue( SimpleDateFormat.getInstance().format(bill.getOrderDate()) );
				cell.setCellStyle(style);
				
				cell = row.createCell(5);
				cell.setCellValue(bill.getAdditionInformation());
				cell.setCellStyle(style);
				
				cell = row.createCell(6);
				cell.setCellValue(bill.getTotal());
				cell.setCellStyle(style);
			
				i++;
		}
		
		
		

		FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
        workbook.write(outputStream);
        workbook.close();
		
		System.out.println("Success");
	}
	
	
	public static void main(String[] args) throws Exception {
		ZoneId defaultZoneId = ZoneId.systemDefault();
//		
//		2020-12-21T21:00:00.000+00:00
		String string = "2020-12-20T21:26:00.000+00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+'00:00", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(string, formatter);
		Date d2= Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		System.out.println("Test: "+date);
		System.out.println("Test: "+d2);
		
//		//yyyy-mm-dd HH:mm:ss
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate date2 = LocalDate.parse(string, formatter);
		System.out.println(date2);
		
		Date d= Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
		System.out.println(d);
		System.out.println(date2);
		
		Date newDate = new Date(2020, 12, 12);
		System.out.println("test new date: " + newDate);
		
		
		
		
		
		
		
		
	}
	
	
	

}
