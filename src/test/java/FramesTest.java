import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FramesTest {
	
	public WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.get("http://pragmatic.bg/automation/lecture13frames/Frames.html");
	}
	
	@Test
	public void testFrameWithIdOrName()
	{
		
		//Activate the frame on left side using it's id attribute
		driver.switchTo().frame("left");
		//Get an element from the frame on left side and verify it's contents
		WebElement leftmsg = driver.findElement(By.tagName("p"));
		assertEquals(leftmsg.getText(), "This is Left Frame");
		
		//Activate the Page, this will move context from frame back to the Page
		driver.switchTo().defaultContent();

		//Activate the frame on right side using it's name attribute
		driver.switchTo().frame("right");
		
		//Get an element from the frame on right side and verify it's contents
		WebElement rightmsg = driver.findElement(By.tagName("p"));
		assertEquals(rightmsg.getText(), "This is Right Frame");
		
		//Activate the Page, this will move context from frame back to the Page	
		driver.switchTo().defaultContent();
		
	}
	
	@Test
	public void testFrameByIndex()
	{
		//Activate the frame in middle using it's index. Index starts at 0
		driver.switchTo().frame(1);
		
		//Get an element from the frame in the middle and verify it's contents
		WebElement leftmsg = driver.findElement(By.tagName("p"));
		assertEquals(leftmsg.getText(), "This Frame doesn't have id or name");
		
		//Activate the Page, this will move context from frame back to the Page
		driver.switchTo().defaultContent();
	}
	
	@Test
	public void testFrameByContents()
	{
		//Get all frames on the Page, created with <frame> tag
		List<WebElement> frames = driver.findElements(By.tagName("frame"));

		//In this example frame in the middle is activated by checking the contents
		//Activate frame and check if it has the desired content. If found perform the operations
		//if not, then switch back to the Page and continue checking next frame
		for(WebElement frame : frames) {
			//switchTo().frame() also accepts frame elements apart from id, name or index 
			driver.switchTo().frame(frame);
			if(driver.getPageSource().contains("This Frame doesn't have id or name")) {
				assertTrue(true, "Middle Frame Found");
				break;
			} else {
				driver.switchTo().defaultContent();
			}
		}

		//Activate the Page, this will move context from frame back to the Page
		driver.switchTo().defaultContent();
	}

	@AfterClass
	public void tearDown()
	{
		//Close the Parent Popup Window
		driver.quit();
	}
}
