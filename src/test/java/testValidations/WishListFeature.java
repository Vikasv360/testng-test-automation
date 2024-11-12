package testValidations;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class WishListFeature {

	@Test
	public void test() throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		List<WebElement> itemList = driver.findElements(By.cssSelector("div[class='product-thumb transition']"));

		for (int i = 0; i < itemList.size(); i++)
		{
			WebElement item = itemList.get(i);
			String itemText = item.getText();
			System.out.println(itemText);
			if (itemText.contains("MacBook")) 
			{
				item.findElement(By.cssSelector("button[data-original-title='Add to Wish List']")).click();
			}
		}	
		
		Thread.sleep(3000);
		String successMsg = driver.findElement(By.cssSelector("div[class*='alert-success']")).getText();
		String expctSuccessMsg = "Success: You have modified your wish list!";
		
//		if(successMsg.contains(expctSuccessMsg)) {
//			driver.findElement(By.xpath("//a[text()='Continue']")).click();
//		}
//		
//		driver.findElement(By.xpath("//a[text()='Qafox.com']")).click();
	}
	
}
