import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowMaximize {
	
	@Test
	public void testRowSelectionUsingControlKey() throws Exception {
		

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://www.abv.bg");
		
		driver.quit();
	}
}
