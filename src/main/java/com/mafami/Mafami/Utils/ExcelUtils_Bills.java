package com.mafami.Mafami.Utils;

import java.io.File;

public class ExcelUtils_Bills {

	public void export() {
		
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xls";
		
//		WritableWorkbook workbook = Workbook.createWorkbook(new File(fileLocation));
		
	}
	
}
