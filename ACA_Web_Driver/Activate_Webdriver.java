package ACA_Web_Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Activate_Webdriver {
    public static WebDriver driver;

    @BeforeTest
    public void openBrowser() 
    {
        System.setProperty("webdriver.edge.driver", "C:\\myFolder\\ACA_HealthCheck_Code\\aca_health_check_suite\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}










/*public static WebDriver driver;

public static WebDriver getdriver()
{
	if(driver==null)
	{
		System.setProperty("webdriver.edge.driver","C:\\Users\\Miriyala.Yamini\\OneDrive - Ipsos\\Documents\\Yamini\\Keyword_Driven_Framework_New\\keyword_driven_framework_for_aca\\edgedriver_win64\\msedgedriver.exe");
	    driver = new EdgeDriver();
	    driver.manage().window().maximize();
	    return driver;
	    
	}
	else 
	{
		return driver;
	}	
}*/