package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPage {

	WebDriver driver;

	public WishListPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[@class='table table-bordered table-hover']/tbody/tr")
	List<WebElement> myWishListRows;

	@FindBy(css = ".alert.alert-success.alert-dismissible")
	WebElement wishListSuccessMsg;

	@FindBy(css = "a[id='wishlist-total']")
	WebElement wishListMenu;

	public void removeOutOfStockItem() {

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

}
