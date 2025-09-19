package Selenium_Assignment;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ACA_Web_Driver.Activate_Webdriver;

public class All_Assignments extends Activate_Webdriver
{
	@Test (enabled = true)
    public void orangeHRMTest() throws InterruptedException
	{
        All_selenium_locators obj = new All_selenium_locators(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(4000);
        obj.getUserName_id().sendKeys("Admin");
        Thread.sleep(2000);
        obj.getPassword().sendKeys("admin123");
        Thread.sleep(2000);
        obj.get_login().click();
        Thread.sleep(2000);
    }
	
	@Test (enabled = true)
    public void wikipediaTest() throws InterruptedException
	{
        All_selenium_locators obj = new All_selenium_locators(driver);
        driver.get("https://www.wikipedia.org/");
        Thread.sleep(4000);
        WebElement link = obj.get_Link();
        String S = link.getText();
        System.out.println(S);
       // link.click();
        driver.findElement(By.linkText(S)).click();
        Thread.sleep(2000);
        System.out.println("Click 'English' using LinkText");
        
        WebElement privacy_policy = obj.get_privacy_policy();
        privacy_policy.click();
        Thread.sleep(2000);
        System.out.println("Click 'Privacy policy' using PartialLinkText."); 
   	
    }
	
	@Test (enabled = false)
	    public void amazonSearchTest()throws InterruptedException 
	    {
	        All_selenium_locators obj = new All_selenium_locators(driver);
	        driver.get("https://www.amazon.in/");
            Thread.sleep(1000);
            obj.get_click_continue().click();
            Thread.sleep(1000);
            obj.get_searchBox().sendKeys("Laptop"); 
            System.out.println("\nSearch 'Laptop' using CSS ID ");
            Thread.sleep(2000);
            
            obj.get_searchButton().click();
            System.out.println("\nClick search button using CSS Attribute");
            Thread.sleep(2000);
          
            obj.get_nth_product().click();
            System.out.println("\nFirst product: " + obj.get_nth_product().getAttribute("alt"));
            Thread.sleep(3000);
            
	    } 
	
	@Test (enabled = false)
	public void flipkart() throws InterruptedException 
	{
    	All_selenium_locators obj = new All_selenium_locators(driver);
    	
        driver.get("https://www.flipkart.com/");
        driver.navigate().refresh();
        Thread.sleep(2000);
       try 
        { 
        	 obj.get_closeLogin().click();
        	 Thread.sleep(2000);
        	 System.out.println("Popup closed successfully");
        }
        catch (NoSuchElementException e) 
        {
            System.out.println("Popup did not appear");
        }
        
        obj.get_searchBox_flipkart().sendKeys("Mobiles",Keys.ENTER); 
        Thread.sleep(2000);
        System.out.println("\nSearch 'Mobiles' using Xpath contains ");
        obj.get_filter().click();
        Thread.sleep(2000);
        obj.get_Apply_filter().click();
        Thread.sleep(2000);
        System.out.println("Apply filter (e.g., “4★ & above”) using XPath axes (following-sibling)");
	}
	
	@Test (enabled = false)
	public void iRctc() throws InterruptedException
	{
		All_selenium_locators obj = new All_selenium_locators(driver);
        driver.get("https://www.irctc.co.in/");
        Thread.sleep(10000);
        obj.get_later().click();
        Thread.sleep(2000);
        obj.get_click_ok().click();
        Thread.sleep(2000);
        
        obj.get_from().sendKeys("Goa");
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> fromStations = wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(obj.from_List));
		
        if (!fromStations.isEmpty())   // Select the first suggestion from the list
		{
		     fromStations.get(0).click();
		     System.out.println("Selected from station: " + fromStations.get(0).getText());
		} 
		else {  System.out.println("No station suggestions appeared for searched text"); }

        Thread.sleep(2000);
        
        obj.get_to().sendKeys("Mysore"); 
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> toStations = wait2.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(obj.to_List));
     
        if (!toStations.isEmpty())  // Select the first suggestion from the list
        {
            toStations.get(0).click();
            System.out.println("Selected to station: " + toStations.get(0).getText());
        } else { System.out.println("No station suggestions appeared for searched text"); }
       
        Thread.sleep(3000);
        
        obj.get_Jdate().click();
        Thread.sleep(2000);
        
     // Current Date
        LocalDate today = LocalDate.now();

        // Max allowed date = today + 2 months
        LocalDate maxAllowedDate = today.plusMonths(2);

        // User given date (change format: dd-MM-yyyy)
        String userDateStr = "10-10-2025";  // <--- Pass user input here
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate userDate = LocalDate.parse(userDateStr, formatter);
        
        if (userDate.isBefore(today)) 
        {
            System.out.println("Cannot select a past date. Please select today or future date.");
        } 
        else if (userDate.isAfter(maxAllowedDate)) 
        {
            System.out.println("Dates beyond " + maxAllowedDate + " are not enabled. Please select within current + 2 months.");
        } 
        else
        {
        	 // Get current month-year from datepicker
            while (true) {
                String displayedMonth = driver.findElement(By.xpath("(//div[@class='ui-datepicker-title']/span[1])[1]")).getText();
                String displayedYear = driver.findElement(By.xpath("(//div[@class='ui-datepicker-title']/span[2])[1]")).getText();

                int displayedMonthNum = monthNameToNumber(displayedMonth);
                int displayedYearNum = Integer.parseInt(displayedYear);

                if (displayedMonthNum == userDate.getMonthValue() && displayedYearNum == userDate.getYear()) {
                    break; // Month-Year matched
                } else {
                    // Click Next button to navigate
                    driver.findElement(By.xpath("//span[text()='Next']")).click();
                    Thread.sleep(500); // Small wait after click
                }
            }

            // Select the day
            String day = String.valueOf(userDate.getDayOfMonth());
            driver.findElement(By.xpath("//a[text()='" + day + "']")).click();
            System.out.println("✅ Date selected successfully: " + userDate);
        }
	}
        

	
	private int monthNameToNumber(String displayedMonth) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Test (enabled = false)
	public void DemoQA() throws InterruptedException
	{
		All_selenium_locators obj = new All_selenium_locators(driver);
        driver.get("https://demoqa.com/elements");
        obj.get_Expand_checkbox().click();
        Thread.sleep(2000);
        obj.get_select_checkbox().click();
        Thread.sleep(2000);
        if (obj.get_textbox().isEnabled()) 
        {
            System.out.println("Text Box is enabled");
        } 
        else 
        {
            System.out.println("Text Box is disabled");
        }
	}
	
	@Test (enabled = false)
	public void Saucedemo() throws InterruptedException
	{
		All_selenium_locators obj = new All_selenium_locators(driver);
        driver.get("https://www.saucedemo.com/");
        obj.get_username().sendKeys("standard_user");
        System.out.println("Username: " + obj.get_username().getAttribute("value"));
        Thread.sleep(2000);
        obj.get_sauce_password().sendKeys("secret_sauce");
        System.out.println("Password: " + obj.get_sauce_password().getAttribute("value"));
        Thread.sleep(2000);
        obj.get_login_Button().click();
        Thread.sleep(2000);
        obj.get_Select_Item().click();
        System.out.println("Search/select a product.");
        Thread.sleep(2000);
        obj.get_Add_Cart().click();
        System.out.println("Add product to cart.");
        Thread.sleep(2000);
        obj.get_click_Cart().click();
        Thread.sleep(2000);
        obj.get_Checkout().click();
        System.out.println("Proceed to check out."); 
        
	}
	
}









/*

 @Test
	 public void Autom_Tasks() throws Exception
	 {
	
	 All_selenium_locators obj = new All_selenium_locators(driver);
	 
	 driver.get("https://www.amazon.in/");
	 //driver.get("https://www.wikipedia.org/"); 
	 //driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	 Thread.sleep(2000);
	 
	/* WebElement username_id = obj.getUserName_id();
	 username_id.sendKeys("Admin"); 
	 Thread.sleep(2000);
	 
	 WebElement password = obj.getPassword();
	 password.sendKeys("admin123");
	 Thread.sleep(2000);
	 
	 WebElement login = obj.get_login();
	 login.click();
	 Thread .sleep(2000);
	 
	 List<WebElement> input_ele = obj.get_input_ele();
     System.out.println("\n Total input elements: " + input_ele.size());
     Thread.sleep(2000); */
     
     // --------------------------------------------Assignment_2 start-----------------------------------------------------------------//
	 /*WebElement link = obj.get_Link();
     String S = link.getText();
     System.out.println(S);
     //link.click(); 
     driver.findElement(By.linkText(S)).click();
     Thread.sleep(2000);
   
     System.out.println("Click 'English' using LinkText");
     
     WebElement privacy_policy = obj.get_privacy_policy();
     privacy_policy.click();
     Thread.sleep(2000);
     System.out.println("Click 'Privacy policy' using PartialLinkText."); */
	
	 // --------------------------------------------Assignment_3 start-----------------------------------------------------------------//
     
	/* WebElement searchBox = obj.get_searchBox();
	 searchBox.sendKeys("Laptop", Keys.ENTER);    
     Thread.sleep(2000);
	 
	}
	@Test
	 public void Autom_Tasks() throws Exception
	 {
	
	 All_selenium_locators obj = new All_selenium_locators(driver);
	 
	 driver.get("https://www.amazon.in/");
	 //driver.get("https://www.wikipedia.org/"); 
	 //driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	 Thread.sleep(2000);
	 
	/* WebElement username_id = obj.getUserName_id();
	 username_id.sendKeys("Admin"); 
	 Thread.sleep(2000);
	 
	 WebElement password = obj.getPassword();
	 password.sendKeys("admin123");
	 Thread.sleep(2000);
	 
	 WebElement login = obj.get_login();
	 login.click();
	 Thread .sleep(2000);
	 
	 List<WebElement> input_ele = obj.get_input_ele();
    System.out.println("\n Total input elements: " + input_ele.size());
    Thread.sleep(2000); */
    
    // --------------------------------------------Assignment_2 start-----------------------------------------------------------------//
	 /*WebElement link = obj.get_Link();
    String S = link.getText();
    System.out.println(S);
    //link.click(); 
    driver.findElement(By.linkText(S)).click();
    Thread.sleep(2000);
  
    System.out.println("Click 'English' using LinkText");
    
    WebElement privacy_policy = obj.get_privacy_policy();
    privacy_policy.click();
    Thread.sleep(2000);
    System.out.println("Click 'Privacy policy' using PartialLinkText."); */
	
	 // --------------------------------------------Assignment_3 start-----------------------------------------------------------------//
    
	/* WebElement searchBox = obj.get_searchBox();
	 searchBox.sendKeys("Laptop", Keys.ENTER);    
    Thread.sleep(2000);
	 
	} 
*/
