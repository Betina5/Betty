import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class WindowPopupTest {
	
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void testWindowPopup() {

		//Save the WindowHandle of Parent Browser Window
		String parentWindowId = driver.getWindowHandle();

		//Clicking Help Button will open Help Page in a new Popup Browser Window
		WebElement helpButton = driver.findElement(By.id("helpbutton"));
		helpButton.click();

		//Switch to the Help Popup Browser Window
		driver.switchTo().window("HelpWindow");

		//Verify the driver context is in Help Popup Browser Window
		assertTrue(driver.getTitle().equals("Help"));

		//Close the Help Popup Window
		driver.close();
		//Move back to the Parent Browser Window
		driver.switchTo().window(parentWindowId);
		//Verify the driver context is in Parent Browser Window
		assertTrue(driver.getTitle().equals("Build my Car - Configuration"));
	}
	
	@Test
	public void testWindowPopupUsingTitle() {
		//Save the WindowHandle of Parent Browser Window
		String parentWindowId = driver.getWindowHandle();

		//Clicking Visit Us Button will open Visit Us Page in a new Popup Browser Window
		WebElement visitButton = driver.findElement(By.id("visitbutton"));
		visitButton.click();

		//Get Handles of all the open Popup Windows
		//Iterate through the set and check if title of each window matches with expected Window Title
		Set<String> allWindows = driver.getWindowHandles();

		if(!allWindows.isEmpty()) {
			for (String windowId : allWindows) {

				try {
					if(driver.switchTo().window(windowId).getTitle().equals("Visit Us")) {
						//Close the Visit Us Popup Window
						driver.close();
						break;
					}
				}
				catch(NoSuchWindowException e) {
					e.printStackTrace();
				}
			}
		}

		//Move back to the Parent Browser Window
		driver.switchTo().window(parentWindowId);
		//Verify the driver context is in Parent Browser Window
		assertTrue(driver.getTitle().equals("Build my Car - Configuration"));
	}
	
	@Test
	public void testWindowPopupUsingContents()
	{
		//Save the WindowHandle of Parent Browser Window
		String currentWindowId = driver.getWindowHandle();
		
		//Clicking Chat Button will open Chat Page in a new Popup Browser Window
		WebElement chatButton = driver.findElement(By.id("chatbutton"));
		chatButton.click();
		
		//There is no name or title provided for Chat Page Popup
		//We will iterate through all the open Windows and check the contents to find
		//out if it's Chat Window
		Set<String> allWindows = driver.getWindowHandles();
		if(!allWindows.isEmpty()) {
			for (String windowId : allWindows) {
				driver.switchTo().window(windowId);
				
				if(driver.getPageSource().contains("Build my Car - Configuration - Online Chat")) {
					try {
				
						//Find the Close Button on Chat Popup Window and close the Popup
						//by clicking Close Button instead of closing it directly
						WebElement closeButton = driver.findElement(By.id("closebutton"));
						closeButton.click();
						break;
					} catch(NoSuchWindowException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//Move back to the Parent Browser Window
		driver.switchTo().window(currentWindowId);
		//Verify the driver context is in Parent Browser Window
		assertTrue(driver.getTitle().equals("Build my Car - Configuration"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		//Close the Parent Popup Window
		driver.quit();
	}
}
