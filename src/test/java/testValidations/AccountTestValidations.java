package testValidations;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTestValidations {

WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		
		driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterClass
	public void tearUp() {
	
		driver.quit();
	}
	
	
	@Test(dataProvider = "createValidData")
	public void accountLogin(String userName, String password) throws InterruptedException {
		
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.cssSelector("input[id='input-email']")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[id='input-password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	
//	@Test(dependsOnMethods = "accountLogin")
//	public void accountLogout() throws InterruptedException {
	
//	}
	
	@DataProvider
	public Object[][] createValidData() {
		Object[][] data = new Object[2][2];
		data[0][0]="vikasv360test@gmail.com";
		data[0][1]="Test@1234";
		data[1][0]="vikas3090@test.com";
		data[1][1]="Test@123";
		
		return data;
	}
}
