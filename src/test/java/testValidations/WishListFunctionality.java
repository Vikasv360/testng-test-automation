package testValidations;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class WishListFunctionality {

	@Test
	public void test() throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.cssSelector("a[title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.cssSelector("input[id='input-email']")).sendKeys("vikasv360test@gmail.com");
		driver.findElement(By.cssSelector("input[id='input-password']")).sendKeys("Test@1234");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		driver.findElement(By.xpath("//a[text()='Qafox.com']")).click();
		
		List<WebElement> itemList = driver.findElements(By.cssSelector("div[class='product-thumb transition']"));

		for (int i = 0; i < itemList.size(); i++)
		{
			WebElement item = itemList.get(i);
			String itemText = item.getText();
//			System.out.println(itemText);
			if (itemText.contains("iPhone")) 
			{
				item.findElement(By.cssSelector("button[data-original-title='Add to Wish List']")).click();
			}
		}
		
		
		Thread.sleep(3000);
		String actWishListTxt = driver.findElement(By.cssSelector("a[id='wishlist-total']")).getText();
		String exptWishListTxt = "Wish List (1)";
		Assert.assertEquals(actWishListTxt, exptWishListTxt);
		
		driver.findElement(By.cssSelector("a[id='wishlist-total']")).click();
		
		List<WebElement> myWishListRows = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr"));
		
		for (int i = 0; i < myWishListRows.size(); i++)
		{
		  
		    WebElement stockCell = myWishListRows.get(i).findElement(By.xpath("td[4]")); 
		    String stockText = stockCell.getText();
		    
		    System.out.println(stockText);
		 
		    if (stockText.contains("Out Of Stock")) 
		    {
		        WebElement removeButton = myWishListRows.get(i).findElement(By.xpath("td[6]//a[@class='btn btn-danger']")); 
		        removeButton.click();
		        break; 
		    }
		    
		}
		
		Thread.sleep(3000);
		String successMsg = driver.findElement(By.cssSelector("div[class*='alert-success']")).getText();
		String expctSuccessMsg = "Success: You have modified your wish list!";
		
		if(successMsg.contains(expctSuccessMsg)) {
			driver.findElement(By.xpath("//a[text()='Continue']")).click();
		}
		
		driver.findElement(By.xpath("//a[text()='Qafox.com']")).click();
	}
}

