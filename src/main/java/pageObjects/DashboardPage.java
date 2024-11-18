package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import reusableComponents.reusableComponent;

public class DashboardPage extends reusableComponent{

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// PageFactory design
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="a[title='My Account']")
	WebElement myAcctMenu;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement loginMenu;
	
	@FindBy(xpath = "//a[text()='Qafox.com']")
	WebElement dashBoardLink;
	
	@FindBy(css = "div[class='product-thumb transition']")
	List<WebElement> itemList;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	WebElement ShopCartBtn;
	
	@FindBy(css="a[id='wishlist-total']")
	WebElement wishListMenu;
	
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logoutBtn;

	
	By wishListSuccessMsg=By.cssSelector(".alert.alert-success.alert-dismissible");
	
	//Go to Login Page
	public void goToLoginPage() {
		
		myAcctMenu.click();
		loginMenu.click();
	}
	
	public void goToDashboardPage() {
		dashBoardLink.click();
	}

	//Search product and add to cart
	public void addProductToCart()
	{

		for (int i = 0; i < itemList.size(); i++)
		{
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			if (itemText.contains("MacBook")) 
			{
				item.findElement(By.xpath("//span[text()='Add to Cart']")).click();

			}
		}
	}
	
	//Click on shopping cart link
	public void goToCart() {
		ShopCartBtn.click();
	}
	
	//Add Product To wishList
	public void AddProductTowishList(){

		for (int i = 0; i < itemList.size(); i++)
		{
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			if (itemText.contains("iPhone")) {
				item.findElement(By.cssSelector("button[data-original-title='Add to Wish List']")).click();
			}
		}
		
		waitForElementToAppear(wishListSuccessMsg);
		
	}
	
	//Go To WishList Page
	public void goToWishListPage() {
		String actWishListTxt = wishListMenu.getText();
		String exptWishListTxt = "Wish List (1)";
		Assert.assertEquals(actWishListTxt, exptWishListTxt);
		wishListMenu.click();
	}
	
	
	public void acctLogout() {
		myAcctMenu.click();
		logoutBtn.click();
	}
}
