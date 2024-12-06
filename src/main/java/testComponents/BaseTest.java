package testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public WebDriver driver;
	
	 public WebDriver getDriver() {
	        return driver;
	    }

    @BeforeSuite
    public void setup() {
        // Read browser and configuration settings
        driver = WebDriverFactory.createDriver("edge");
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	
}