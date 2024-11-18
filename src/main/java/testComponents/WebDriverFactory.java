package testComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {

	public static WebDriver createDriver(String browser) {
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                // Set ChromeDriver path if needed
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
