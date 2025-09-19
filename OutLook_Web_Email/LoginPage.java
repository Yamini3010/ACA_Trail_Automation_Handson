package OutLook_Web_Email;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
	   private WebDriver driver;
	    private WebDriverWait wait;

	    private By emailInput = By.name("loginfmt");
	    private By nextButton = By.id("idSIButton9");
	    private By passwordInput = By.name("passwd");
	    private By signInButton = By.id("idSIButton9");
	    private By staySignedInYes = By.xpath("//input[@type='submit' and @value='Yes']");
	   // private By staySignedInNo = By.id("idBtn_Back");

	    public LoginPage(WebDriver driver) 
	    {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }
	  
	    public void enterUsername(String username) 
	    {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(username);
	        driver.findElement(nextButton).click();
	    }
	    public void enterPassword(String password) 
	    {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
	        driver.findElement(signInButton).click();
	    }
	    public void handleStaySignedIn() {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(staySignedInYes)).click();
	        } catch (Exception e) {
	           
	        }
	    }
}
