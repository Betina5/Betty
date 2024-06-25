import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ScreenshotTests {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("L2AGLb")).click();
	}
  
	@Test //open class ScreenshotTests
	public void testTakesScreenshot()
	{
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File scrFile = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File("D:\\NewFolder\\main_page.png"));

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Test
	public void testElementScreenshot() {
		WebElement pmoabsdiv = driver.findElement(By.cssSelector(".lnXdpd"));

		try {
			FileUtils.copyFile(pmoabsdiv.getScreenshotAs(OutputType.FILE), new File("D:\\NewFolder\\div.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void teadDown()
	{

		driver.quit();
	}
}