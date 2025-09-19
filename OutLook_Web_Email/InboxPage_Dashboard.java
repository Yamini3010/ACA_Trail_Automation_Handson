package OutLook_Web_Email;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.FileInputStream;
import org.openqa.selenium.edge.EdgeOptions;



public class InboxPage_Dashboard 
{
	    WebDriver driver;
	    WebDriverWait wait;
	    Properties prop;
	    LoginPage loginPage;
	    
	    public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
		public static String path = System.getProperty("user.dir");
		ExtentTest test;

	    // Locators
	    private By newMailBtn = By.xpath("//button//span[text()='New mail']");
	    private By profileIcon = By.id("meInitialsButton");
	    private By signOutBtn = By.id("mectrl_body_signOut");
	    
	    public String captureScreenshot(WebDriver driver) {
	        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	        return "<img src=\"data:image/png;base64," + base64Screenshot
	                + "\" alt=\"Screenshot\" style=\"max-width: 1000px; height: auto; display: block; margin: auto;\"/>";
	    }
	    
	    @BeforeSuite
	    public void Setup_ExtentReports_And_Screenshots()
	    {
	    	String dateName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String folderName = "ExtentReports_Outlook_" + dateName;
			spark = new ExtentSparkReporter(path + "\\test-output\\" + folderName + "\\Outlook_ExtentReport.html");
			//spark.config().setTheme(Theme.DARK);
	        spark.config().setCss("img { width: 1000px !important; height: auto !important; display: block; margin: auto; }"); // Optional styling
	        spark.config().setJs("document.body.style.zoom = '100%';"); // Optional styling
	        
	        extent = new ExtentReports();
			extent.attachReporter(spark);
			
			// Add environment info
	        extent.setSystemInfo("Browser", "Chrome");
	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Tester", "Yamini");
		    
	    }
	    

	    @BeforeClass
	    public void setUp() throws IOException 
	    {
	        prop = new Properties();
	        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	        prop.load(fis);
	        
	        // Launch browser
	        
	        //System.setProperty("webdriver.edge.driver", "msedgedriver.exe path");
	        System.setProperty("webdriver.edge.driver", "C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
	        EdgeOptions options = new EdgeOptions();
	        options.addArguments("InPrivate");
	        driver = new EdgeDriver(options);
	        driver.manage().window().maximize();
	       
	        driver.get(prop.getProperty("url"));
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(50));

	       // WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
	        loginPage = new LoginPage(driver);
	    }

	    @Test(priority = 1)
	    public void verifyLoginAndInboxElements() 
	    { 
	       test = extent.createTest("<u><b><font color=blue>" + "Verify Outlook Inbox Dashboard Elements" + "	</font></b></u>");
           try 
           {
        	    loginPage.enterUsername(prop.getProperty("username"));
        	    test.pass("Entered Username : <br>" + captureScreenshot(driver));
        
			    
                loginPage.enterPassword(prop.getProperty("password"));
                test.pass("Entered Password : <br>" + captureScreenshot(driver));
                
			    
                loginPage.handleStaySignedIn();
                test.log(Status.INFO, "<b><font>" + "Successfully logged in with test user" +"</b></font>");
                test.pass("StaySigned in Yes <br>" + captureScreenshot(driver));
                
        		
	       
		        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(newMailBtn)).isDisplayed(), "New Mail button not visible");
		        test.pass("New Mail button is visible <br>" + captureScreenshot(driver));
		        
		       
		        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon)).isDisplayed(), "Profile icon not visible");
		        test.pass("Profile icon is visible <br>" + captureScreenshot(driver));
		        
		        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("meInitialsButton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", profile);
				 test.log(Status.INFO, "Clicked on Profile icon <br>" + captureScreenshot(driver));
		       
		       
		        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(signOutBtn)).isDisplayed(), "Sign Out not visible");
		        test.pass("Sign Out button is visible <br>" + captureScreenshot(driver));
		        
		        //test.info("All the testcases are passed");
	       }
           catch (Exception e) 
           {
               test.log(Status.FAIL, "Test failed: " + e.getMessage());
               test.fail("Screenshot on failure <br>" + captureScreenshot(driver));
               Assert.fail("Test failed due to exception: " + e.getMessage());
           }
           
	    }
	    
	    @Test(priority = 2)
	    public void verifyPageTitle() 
	    {    
           
           //Another Outlook Task just added few steps and combined in the same outlook code  
           
           test = extent.createTest("<u><b><font color=blue>" + "Verify Outlook Title" + "	</font></b></u>");
           try 
           {
	           String pageTitle = driver.getTitle();
	           test.info("Page title is: " + pageTitle);
	           
	           Assert.assertTrue(pageTitle.contains("Outlook"), "Page title does not contain Outlook");
	           test.log(Status.PASS,"Title contains 'Outlook' <br>" + captureScreenshot(driver)); 
           }
                 
           catch (Exception e)
           {
        	   test.log(Status.FAIL,"Test failed due to exception: <br>" + e.getMessage() +captureScreenshot(driver));
        	   Assert.fail("Test failed due to exception: " + e.getMessage());
	       }
	    }

           
	    @AfterClass
	    public void tearDown() 
	    {
	    	 if (driver != null) 
	    	 {
	             driver.quit();
	         }
	         if (extent != null) 
	         	{
	             extent.flush(); // Generate the report
	         }
	    }
}
