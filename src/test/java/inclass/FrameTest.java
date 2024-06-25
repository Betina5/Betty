package inclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FrameTest {
    public WebDriver driver;

    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("http://pragmatic.bg/automation/lecture13frames/Frames.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void testFrameWithIdOrName() {
        driver.switchTo().frame("left");
        WebElement leftmsg = driver.findElement(By.tagName("p"));
        assertEquals(leftmsg.getText(), "This is Left Frame");

        driver.switchTo().defaultContent();

        driver.switchTo().frame("right");

        WebElement rightmsg = driver.findElement(By.tagName("p"));
        assertEquals(rightmsg.getText(), "This is Right Frame");

        driver.switchTo().defaultContent();
    }
}
