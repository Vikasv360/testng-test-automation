package reusableComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class reusableComponent {

	WebDriver driver;

	public reusableComponent(WebDriver driver) {

		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

		} catch (Exception e) {

			System.out.println("Exception : " + e);
		}
	}

	public void waitForElementToDisappear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(findBy));

		} catch (Exception e) {

			System.out.println("Exception : " + e);
		}
	}
}
