import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class ExecuteJavaScript {

	private WebDriver driver;

	@BeforeMethod
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.get("http://www.abv.bg");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test   //open class ExecuteJavaScript
	public void testJavaScriptCalls() throws Exception
	{

		JavascriptExecutor js = (JavascriptExecutor) this.driver;

		String pageTitle = (String) js.executeScript("return document.title");

		Assert.assertEquals(pageTitle, "АБВ Поща");



		long links = (Long) js.executeScript("var links = document.getElementsByTagName('A'); return links.length");
		Assert.assertEquals(links, 102);
			
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();

	}
}