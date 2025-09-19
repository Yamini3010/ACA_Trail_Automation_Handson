package Testng_Extent_Report_Ex;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AmazonHomePageTest 
{
	    WebDriver driver;
	    public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
	    public static String path = System.getProperty("user.dir");
	    ExtentTest test;
	    public String captureScreenshot(WebDriver driver) 
	    {
	        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	        return "<img src=\"data:image/png;base64," + base64Screenshot
	                + "\" alt=\"Screenshot\" style=\"max-width: 1000px; height: auto; display: block; margin: auto;\"/>";
	    }

	    @BeforeSuite
	    public void setUpBrowser() 
	    {
	    	
	    	ExtentSparkReporter spark = new ExtentSparkReporter("Extent_reports.html"); //passing a Extent reports file name should be...
	        extent = new ExtentReports();
	        extent.attachReporter(spark); 
	        
	        /*Types of HTML reports can be generated
	        ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
	        extent = new ExtentReports();
            extent.attachReporter(spark);
	        
	        ExtentLoggerReporter logger = new ExtentLoggerReporter("logger.html");
	        extent.attachReporter(logger);
            
            
            // ==================== Allure Setup ====================
            // For Allure, no explicit setup is required in code.
            // Allure generates report from the allure-results folder automatically.
            System.out.println("Allure will generate report after tests execution.");
           // ========================================================= */
	        
	    	System.setProperty("webdriver.edge.driver", "C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
	        driver = new EdgeDriver();
	        driver.manage().window().maximize();
	    }

	    @BeforeMethod
	    public void launchAmazonURL() 
	    {
	        System.out.println("Navigating to Amazon home page");
	        driver.get("https://www.amazon.in/");
	    }

	    @Test
	    public void verifyAmazonTitle() {
	        test = extent.createTest("Verify Amazon Title");

	        String title = driver.getTitle();
	        test.info("Page Title fetched: " + title);


	        if (title.contains("Amazon")) 
	        {
	        	test.log(Status.PASS,	"Title contain Amazon<br>" + captureScreenshot(driver));	
	        }
	        else 
	        {
	        	test.log(Status.FAIL, "Title does not contain Amazon<br>" + captureScreenshot(driver));
	        }	        
	    }
	       

	    @AfterSuite
	    public void tearDownBrowser() 
	    {
	        driver.quit();
	        extent.flush(); // Generates the report
	    }
}
