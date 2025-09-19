package KDF;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DriverScript 
{
	@Test
	public void amazonLoginTest() throws Exception {
	       
	        System.setProperty("webdriver.edge.driver", "C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
	        WebDriver driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        
	        Actions obj = new Actions(driver);

	        driver.get(obj.getProperty("url"));

	        // Read credentials from Excel
	        String username = "";
	        String password = "";
	        try 
	        {
	           
	        	FileInputStream fis = new FileInputStream("src/test/resources/Excel_Data/Excel_Outlook_Instructions.xlsx");
	            Workbook wb = new XSSFWorkbook(fis);
	            Sheet sheet = wb.getSheet("Sheet1");

	            username = sheet.getRow(1).getCell(0).getStringCellValue();
	            password = sheet.getRow(1).getCell(1).getStringCellValue();
	            wb.close();
	            
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }

	        // Perform Login
	        obj.click("continue");
	        obj.click("signin_button");
	        obj.type("email_field", username);
	        obj.click("continue_button");
	        obj.type("password_field", password);
	        obj.click("login_button");

	        System.out.println("Amazon login attempted with credentials from Excel.");

	        driver.quit();
	    }
}


