package testValidations;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestValidations {

	WebDriver driver;

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

		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.cssSelector("input[id='input-email']")).sendKeys("vikasv360test@gmail.com");
		driver.findElement(By.cssSelector("input[id='input-password']")).sendKeys("Test@1234");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

	}

	@Test(dependsOnMethods = "accountLogin")
	public void dashboard() {

		driver.findElement(By.xpath("//a[text()='Qafox.com']")).click();
		List<WebElement> itemList = driver.findElements(By.cssSelector("div[class='product-thumb transition']"));

		for (int i = 0; i < itemList.size(); i++) {
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			System.out.println(itemText);
			if (itemText.contains("MacBook")) {
				item.findElement(By.xpath("//span[text()='Add to Cart']")).click();

			}
		}

		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();

		String alertText = driver.findElement(By.cssSelector("div[class*='alert-danger']")).getText();
		String exptAlertText = "Products marked with *** are not available in the desired quantity or not in stock!";

		if (alertText.contains(exptAlertText)) {
			driver.findElement(By.cssSelector("button[class='btn btn-danger']")).click();
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("button[class='btn btn-danger']")));

		} catch (Exception e) {

			System.out.println("Exception : " + e);
		}

		String shpCartTxt = driver.findElement(By.cssSelector("div[id='content'] p")).getText();
		String exptShpCartTxt = "Your shopping cart is empty!";

		System.out.println(shpCartTxt);

		if (shpCartTxt.contains(exptShpCartTxt)) {
			driver.findElement(By.xpath("//a[text()='Continue']")).click();

		}

	}

	@Test(dependsOnMethods = "accountLogin")
	public void wishListFunct() throws InterruptedException {
		List<WebElement> itemList = driver.findElements(By.cssSelector("div[class='product-thumb transition']"));

		for (int i = 0; i < itemList.size(); i++) {
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			if (itemText.contains("iPhone")) {
				item.findElement(By.cssSelector("button[data-original-title='Add to Wish List']")).click();
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(		
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));

		} catch (Exception e) {

			System.out.println("Exception : " + e);
		}

		String actWishListTxt = driver.findElement(By.cssSelector("a[id='wishlist-total']")).getText();
		String exptWishListTxt = "Wish List (1)";
		Assert.assertEquals(actWishListTxt, exptWishListTxt);

		
		driver.findElement(By.cssSelector("a[id='wishlist-total']")).click();

		List<WebElement> myWishListRows = driver
				.findElements(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr"));

		for (int i = 0; i < myWishListRows.size(); i++) {

			WebElement stockCell = myWishListRows.get(i).findElement(By.xpath("td[4]"));
			String stockText = stockCell.getText();

			System.out.println(stockText);

			if (stockText.contains("Out Of Stock")) {
				WebElement removeButton = myWishListRows.get(i)
						.findElement(By.xpath("td[6]//a[@class='btn btn-danger']"));
				removeButton.click();
				break;
			}

		}
	}

	@Test(dependsOnMethods = "accountLogin")
	public void accountLogout() throws InterruptedException {

		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

}
