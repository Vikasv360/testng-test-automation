package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableComponents.reusableComponent;

public class ShoppingCart extends reusableComponent{

	WebDriver driver;

	public ShoppingCart(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css="div[class*='alert-danger']")
	WebElement alertMessage;

	@FindBy(css="button[class='btn btn-danger']")
	WebElement removeButton;
	
	By removeButtonBy= By.cssSelector("button[class='btn btn-danger']");
	
	@FindBy(css="div[id='content'] p")
	WebElement emptyCartMsg;
	
	@FindBy(xpath ="//a[text()='Continue']")
	WebElement continueBtn;
	

	public void removeOutOfStockProduct() {

		String alertText = alertMessage.getText();
		String exptAlertText = "Products marked with *** are not available in the desired quantity or not in stock!";

		if (alertText.contains(exptAlertText)) {
			removeButton.click();
		}
		
		waitForElementToDisappear(removeButtonBy);

	}

	public void clickOnContinueBtn() {

		String shpCartTxt = emptyCartMsg.getText();
		String exptShpCartTxt = "Your shopping cart is empty!";

		System.out.println(shpCartTxt);

		if (shpCartTxt.contains(exptShpCartTxt)) {
			continueBtn.click();
		}

	}
	
}
