package KDF;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions
{
	WebDriver driver;
    Properties prop;

    // Constructor
    public Actions(WebDriver driver) 
    {
        this.driver = driver;
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/Amazon_Loc.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get locator from properties
    public By getLocator(String key) {
        return By.xpath(prop.getProperty(key));
    }

    // Reusable methods
    public void click(String locatorKey) {
        driver.findElement(getLocator(locatorKey)).click();
    }

    public void type(String locatorKey, String value) 
    {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
    	    driver.findElement(getLocator(locatorKey)).sendKeys(value);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
	   		
}
