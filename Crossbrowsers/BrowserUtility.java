package Crossbrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserUtility 
{
	WebDriver driver;

    @Test
    @Parameters("browser")
    public void launchBrowser(String browserName) {
        System.out.println("Browser passed from testng.xml: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {  	
        	System.setProperty("webdriver.edge.driver", "C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}
