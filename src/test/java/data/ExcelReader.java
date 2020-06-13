package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	static FileInputStream fis = null;
	public FileInputStream getFileInputStream () {
		String filepath = System.getProperty("user.dir") + "/src/test/java/data/UserData.xlsx";
		File srcfile = new File(filepath);
		try {
			fis = new FileInputStream(srcfile);
		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
			System.exit(0);
		}
		return fis;
	}
	public Object[][] getExcelData() throws IOException {
		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int TotalNumberofRows = (sheet.getLastRowNum()+1);
		int TotalNumberofColumns = 5 ;
		String[][] arrayExcelData = new String[TotalNumberofRows][TotalNumberofColumns];

		for (int i = 0; i < TotalNumberofRows; i++) {
			for (int j = 0; j < 2; j++) 
			{
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = row.getCell(j).toString();	
			}
			Cell cell;
			XSSFRow roww = sheet.getRow(i);
			cell = roww.getCell(2);
			arrayExcelData[i][2] = new BigDecimal(cell.getNumericCellValue()).toPlainString();
			for (int j = 3; j <TotalNumberofColumns; j++) 
			{
				XSSFRow rowww = sheet.getRow(i);
				arrayExcelData[i][j] = rowww.getCell(j).toString();	
			}
		}
		wb.close();
		return arrayExcelData;
	}
}
