package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="input[id='input-email']")
	WebElement emailId;
	
	@FindBy(css="input[id='input-password']")
	WebElement passwordInput;
	
	@FindBy(css="input[type='submit']")
	WebElement submitBtn;
	
	
	public void enterCredentials(String username,String password) {
		
		emailId.sendKeys(username);
		passwordInput.sendKeys(password);
	}
	
	public void clickOnLogin() {
		submitBtn.click();
	}
	
}