package testValidations;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DashBoardFunctionality {

	@Test
	public void test() throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();

		List<WebElement> itemList = driver.findElements(By.cssSelector("div[class='product-thumb transition']"));

		for (int i = 0; i < itemList.size(); i++)
		{
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			if (itemText.contains("MacBook")) 
			{
				item.findElement(By.xpath("//span[text()='Add to Cart']")).click();

			}
		}
		
		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();
		
		String alertText = driver.findElement(By.cssSelector("div[class*='alert-danger']")).getText();
		String exptAlertText = "Products marked with *** are not available in the desired quantity or not in stock!";
		
		if(alertText.contains(exptAlertText)) {
			driver.findElement(By.cssSelector("button[class='btn btn-danger']")).click();
		}
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("button[class='btn btn-danger']")));

		}
		catch(Exception e) {
			
			System.out.println("Exception : " +e);
		}
		
		String shpCartTxt = driver.findElement(By.cssSelector("div[id='content'] p")).getText();
		String exptShpCartTxt = "Your shopping cart is empty!";
		
		System.out.println(shpCartTxt);

		if(shpCartTxt.contains(exptShpCartTxt)) {
			driver.findElement(By.xpath("//a[text()='Continue']")).click();
			
		}
		
	}
}
