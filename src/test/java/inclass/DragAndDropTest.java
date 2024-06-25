package inclass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class DragAndDropTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("http://pragmatic.bg/automation/lecture13/DragDropDemo.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDragAndDrop() {
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        builder.dragAndDrop(draggable,droppable).perform();

        Assert.assertEquals(droppable.getText(), "Dropped!");
    }

    @Test
    public void testCustomDragAndDrop() {
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        builder
                .clickAndHold(draggable)
                .moveToElement(droppable)
                .release()
                .perform();


        Assert.assertEquals(droppable.getText(), "Dropped!");
    }

    @Test
    public void testCustomDragAndDropBuld() {
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        Action customDragAndDrop = builder
                .clickAndHold(draggable)
                .moveToElement(droppable)
                .release()
                .build();

        customDragAndDrop.perform();
        Assert.assertEquals(droppable.getText(), "Dropped!");
    }
}
