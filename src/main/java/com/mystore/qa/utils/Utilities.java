package com.mystore.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String generateTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "hello"+timeStamp+"@yopmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File("D:\\New folder\\MyStore\\src\\main\\java\\com\\mystore\\qa\\testdata\\MyStore.xlsx");
		XSSFWorkbook workBook = null;
		try {
			FileInputStream excelStream = new FileInputStream(excelFile);
			workBook = new XSSFWorkbook(excelStream);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workBook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum(); //to get number of rows
		int cols = sheet.getRow(0).getLastCellNum();//to get number of columns
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0; i<rows; i++){
			
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0; j<cols; j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString( (int) cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
					}
			}
		}
		
		return data;
		}
	
	//take screenshot
	public static String captureScreenshot(WebDriver driver, String testName) {
		File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenShotPath = System.getProperty("user.dir")+"\\Screenshot\\" +testName+".png";
	//for copying the screenshot from srcScreenShot to destinationScreenShotPath
		try {
			FileHandler.copy(srcScreenShot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenShotPath;
	}
	
}
