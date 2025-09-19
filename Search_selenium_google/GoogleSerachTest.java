package Search_selenium_google;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GoogleSerachTest 
{
	WebDriver driver;
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    ExtentTest test;

    // Screenshot utility (Base64 + embedded in report)
    public String captureScreenshot(WebDriver driver) {
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return "<img src=\"data:image/png;base64," + base64Screenshot
                + "\" alt=\"Screenshot\" style=\"max-width: 1000px; height: auto; display: block; margin: auto;\"/>";
    }

    @BeforeSuite
    public void setUpReportAndBrowser() {
        spark = new ExtentSparkReporter("Extent_Reports.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

       // System.setProperty("webdriver.edge.driver","C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
       // driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void launchGoogle() {
        driver.get("https://www.google.com");
        driver.navigate().refresh();
    }

    @Test
    public void verifyFirstResultContainsSelenium() {
        test = extent.createTest("Google Search - Verify First Result Contains Selenium");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver",Keys.ENTER);
      
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jZ2SBf")));
        //firstResult.click();
        String resultText = firstResult.getText();
        test.info("First result text: " + resultText); 
        
       // WebElement checkbox = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[1]"));
       // checkbox.click();

        Assert.assertTrue(resultText.contains("Selenium"),
                "First result does not contain 'Selenium'. Found: " + resultText);

        // If assert passes, mark test as PASS
        test.log(Status.PASS, "First result contains Selenium<br>" + captureScreenshot(driver));
    }

    @AfterMethod
    public void checkTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable() + "<br>" + captureScreenshot(driver));
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        extent.flush(); // Generate Extent Report
    }

}
