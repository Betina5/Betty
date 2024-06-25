import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class DragDropTest {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.get("http://pragmatic.bg/automation/lecture13/DragDropDemo.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void testDragDrop() throws InterruptedException {

		WebElement source = this.driver.findElement(By.id("draggable"));
		WebElement target = this.driver.findElement(By.id("droppable"));

		Actions builder = new Actions(this.driver);

		builder.dragAndDrop(source, target).perform();

		assertEquals(target.getText(), "Dropped!");
	}

	@Test
	public void dragAndDrop() {
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));

		Actions builder = new Actions(driver);

		builder.dragAndDrop(source, target).perform();

		builder.clickAndHold(source)
				.moveToElement(target)
				.release()
				.perform();

		assertEquals(target.getText(), "Dropped!");

	}

	@Test
	public void testDragAndDrop() {
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));

		Actions builder = new Actions(driver);

		builder.dragAndDrop(source, target).perform();

		Actions dragAndDropSourceToTarget = builder.clickAndHold(source).moveToElement(target).release();

		dragAndDropSourceToTarget.perform();

		assertEquals(target.getText(), "Dropped!");

	}

	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();

	}
}