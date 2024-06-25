package inclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testWindowPopup() {
        driver.findElement(By.id("helpbutton")).click();
        driver.switchTo().window("HelpWindow");
        WebElement heading = driver.findElement(By.cssSelector(".demo>h3"));
        String headingText = heading.getText();
        Assert.assertEquals(headingText, "Build my Car - Configuration Help");
    }

    @Test
    public void testWindowPopupHandle() {
        String parentWindowHandle = driver.getWindowHandle();
        driver.findElement(By.id("chatbutton")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            String pageSource = driver.switchTo().window(windowHandle).getPageSource();
            if(pageSource.contains("Build my Car - Configuration - Online Chat")){
                break;
            }
        }
        String actualText = driver.findElement(By.cssSelector(".demo>p")).getText();
        Assert.assertEquals(actualText, "Wait while we connect you to Chat...");

        driver.switchTo().window(parentWindowHandle);
        Assert.assertEquals(driver.getTitle(), "Build my Car - Configuration");

    }
}
