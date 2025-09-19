package Selenium_Assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class All_selenium_locators 
{
	WebDriver driver;
	By username_id = By.name("username");
	By password = By.name("password");
	By login = By.className("orangehrm-login-button");
	By input_ele = By.tagName("input");
	
	//------------------------------Assignment 2 starts--------------------//
	
	By link = By.id("js-link-box-en");
	//By link = By.linkText("7,050,000+ articles");
	By privacy_policy = By.partialLinkText("Privacy");
	
	//------------------------------Assignment 3 starts--------------------//
	
	By searchBox = By.cssSelector("#twotabsearchtextbox");
	By click_continue = By.xpath("/html/body/div/div[1]/div[3]/div/div/form/div/div/span/span/button");
	By searchButton = By.cssSelector("input[value='Go']");
	By nth_product = By.cssSelector("div.s-result-item:nth-child(1) img.s-image");
	
	//------------------------------Assignment 4 starts--------------------//
	By closeLogin = By.className("_30XB9F");
	By searchBox_flipkart = By.xpath("//input[contains(@title,'Search')]");
	By filter = By.xpath("(//*[@class='FtQCb2 _3Owiq+'])[3]");
	By Apply_filter = By.xpath("//div[@class='XqNaEv']/following-sibling::div[text()='4★ & above']");
	
	//------------------------------Assignment 5 starts--------------------//
	By later = By.id("iz-optin-wp-btn1Txt");
	By click_ok = By.xpath("//button[text()='OK']");
	By from = By.xpath("//input[starts-with(@class,'ng-tns') and @role='searchbox']");
	By from_List = By.xpath("//ul[@role='listbox']//li");
	By to_List = By.xpath("//ul[@role='listbox' and not(contains(@style,'display: none'))]//li");
	By to = By.xpath("//input[contains(@class,'ng-tns-c58-9') and @role='searchbox']");
	By Jdate = By.xpath("//p-calendar[@formcontrolname='journeyDate']//input[@type='text']");
	By monthYear = By.className("ui-datepicker-header");          ////div[contains(@class,'datepicker-title') or contains(@class,'ui-datepicker-title')]
	By next_btn = By.className("ui-datepicker-next-icon");

//	By next_Month = By.xpath("//span[contains(@class,'chevron-right')]");
//	By day = By.className("//table[contains(@class,'ui-datepicker-calendar')]//td[not(contains(@class,'ui-datepicker-unselectable'))]//a[text()='\" + dayToSelect + \"']");
	
	
	//------------------------------Assignment 6 starts--------------------//
	By Expand_checkbox = By.xpath("//span[@class='text' and text()='Check Box']");
	By select_checkbox = By.cssSelector("#tree-node > ol > li > span > label > span.rct-checkbox > svg");
	By textbox = By.xpath("//span[text()='Text Box']");	
	
	//------------------------------Assignment 7 starts--------------------//
	By username = By.id("user-name");
	By sauce_password = By.xpath("//input[@class = 'input_error form_input'and @id = 'password']");
	By Select_Item = By.id("item_0_title_link");
	By Add_Cart = By.id("add-to-cart");
	By click_Cart = By.className("shopping_cart_badge");   //By.id("shopping_cart_link");
	By Checkout = By.cssSelector("#checkout");
	By login_Button = By.xpath("//input[@name = 'login-button']");
	
	
	
	

	
	public All_selenium_locators(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getUserName_id()
	{
	    return driver.findElement(username_id);
	}
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	public WebElement get_login()
	{
		return driver.findElement(login);
	}
	public List<WebElement> get_input_ele()
	{
		return driver.findElements(input_ele);
	}
	
	//------------------------------Assignment 2 starts--------------------//
	
	public WebElement get_Link()
	{
	    return driver.findElement(link);
	}
	public WebElement get_privacy_policy()
	{
	    return driver.findElement(privacy_policy);
	}
	//------------------------------Assignment 3 starts--------------------//
    
	public WebElement get_searchBox()
	{
		 return driver.findElement(searchBox);
	}
	public WebElement get_click_continue()
	{
		return driver.findElement(click_continue);
	}
	public WebElement get_searchButton() 
	{
		return driver.findElement(searchButton);
	}
	public WebElement get_nth_product()
	{
		return driver.findElement(nth_product);
	}
	//------------------------------Assignment 4 starts--------------------//
	
   public WebElement get_closeLogin()
   {
	   return driver.findElement(closeLogin);
   }
   public WebElement get_searchBox_flipkart()
   {
	   return driver.findElement(searchBox_flipkart);
   }
   public WebElement get_filter()
   {
	   return driver.findElement(filter);
   }
   public WebElement get_Apply_filter()
   {
	   return driver.findElement(Apply_filter);
   }

  //------------------------------Assignment 5 starts--------------------//
   public WebElement get_later()
   {
	   return driver.findElement(later);
   }
   public WebElement get_click_ok()
   {
	   return driver.findElement(click_ok);
   }
   public WebElement get_from()
   {
	   return driver.findElement(from);
   }
   public List<WebElement> get_from_List() 
   {
	    return driver.findElements(from_List);
   }
   public WebElement get_to()
   { 
	   return driver.findElement(to);
   }
   public List<WebElement> get_to_List() {
	    return driver.findElements(to_List);
	}
   public WebElement get_Jdate()
   { 
	   return driver.findElement(Jdate);
   }
  
   public WebElement get_next_btn()
   { 
	   return driver.findElement(next_btn);
   }

   //------------------------------Assignment 6 starts--------------------//
   
   public WebElement get_Expand_checkbox()
   {
	   return driver.findElement(Expand_checkbox);
   }
   public WebElement get_select_checkbox()
   {
	   return driver.findElement(select_checkbox);
   }
   public WebElement get_textbox()
   {
	   return driver.findElement(textbox);
   }
   
 //------------------------------Assignment 7 starts--------------------//
   public WebElement get_username()
   {
	   return driver.findElement(username);
   }
   public WebElement get_sauce_password()
   {
	   return driver.findElement(sauce_password);
   }
   public WebElement get_Select_Item()
   {
	   return driver.findElement(Select_Item);
   }
   public WebElement get_Add_Cart()
   {
	   return driver.findElement(Add_Cart);
   }
   public WebElement get_click_Cart()
   {
	   return driver.findElement(click_Cart);
   }
   public WebElement get_Checkout()
   {
	   return driver.findElement(Checkout);
   }
   public WebElement get_login_Button()
   {
	   return driver.findElement(login_Button);
   }
   
   
   
	
}
