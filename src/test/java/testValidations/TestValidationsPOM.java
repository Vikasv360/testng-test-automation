package testValidations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;

public class TestValidationsPOM {

	WebDriver driver;
	DashboardPage dashboardPage;

	@BeforeClass
	public void setUp() {

		driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearUp() {

		driver.quit();
	}

	@Test
	public void accountLogin() {

		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.goToLoginPage();
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterCredentials("vikasv360test@gmail.com", "Test@1234");
		loginPage.clickOnLogin();
		

	}

	@Test(dependsOnMethods = "accountLogin")
	public void dashboard() {

		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.goToDashboardPage();
		dashboardPage.addProductToCart();
		dashboardPage.goToCart();

		ShoppingCart shoppingCart = new ShoppingCart(driver);
		shoppingCart.removeOutOfStockProduct();
		shoppingCart.clickOnContinueBtn();
		

	}

	@Test(dependsOnMethods = "accountLogin")
	public void wishListFunct() throws InterruptedException {
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.AddProductTowishList();
		dashboardPage.goToWishListPage();
		
		WishListPage wishListPage = new WishListPage(driver);
		wishListPage.removeOutOfStockItem();
	
	}

	@Test(dependsOnMethods = "accountLogin")
	public void accountLogout() throws InterruptedException {

		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

}
