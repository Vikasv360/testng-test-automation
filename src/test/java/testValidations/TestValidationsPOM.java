package testValidations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testComponents.BaseTest;

public class TestValidationsPOM extends BaseTest{

	@Test(dataProvider = "getValidCred")
	public void accountLogin(String email,String password) {

		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.goToLoginPage();
	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterCredentials(email,password);
		loginPage.clickOnLogin();
		

	}
	
	@DataProvider
	public Object[][] getValidCred() {
		
		return new Object[][] {{"vikasv360test@gmail.com", "Test@1234"}} ;
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
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.acctLogout();

	}

}
