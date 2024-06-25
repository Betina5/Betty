package inclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelectTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSelectByValue() {
        WebElement makeSelect = driver.findElement(By.name("make"));
        Select make = new Select(makeSelect);
        make.selectByValue("mercedes");
        WebElement firstSelectedOption = make.getFirstSelectedOption();
        Assert.assertTrue(firstSelectedOption.isSelected());
    }

    @Test
    public void testSelectByIndex() {
        WebElement makeSelect = driver.findElement(By.name("make"));
        Select make = new Select(makeSelect);
        make.selectByIndex(2);
        WebElement firstSelectedOption = make.getFirstSelectedOption();
        String firstSelectedOptionText = firstSelectedOption.getText();
        Assert.assertEquals(firstSelectedOptionText, "Audi");
    }

    @Test
    public void testSelectByVisibleText() {
        WebElement makeSelect = driver.findElement(By.name("make"));
        Select make = new Select(makeSelect);
        make.selectByVisibleText("Honda");
        WebElement firstSelectedOption = make.getFirstSelectedOption();
        String firstSelectedOptionValue = firstSelectedOption.getAttribute("value");
        Assert.assertEquals(firstSelectedOptionValue, "honda");
    }

    @Test
    public void testSelect() {
        WebElement makeSelect = driver.findElement(By.name("make"));
        Select make = new Select(makeSelect);
        Assert.assertFalse(make.isMultiple());
    }

    @Test
    public void testMultipleSelect() {
        WebElement colorSelect = driver.findElement(By.name("color"));
        Select color = new Select(colorSelect);
        Assert.assertTrue(color.isMultiple());

        color.selectByValue("bl");
        color.selectByValue("rd");
        color.selectByValue("sl");

        List<WebElement> allSelectedOptions = color.getAllSelectedOptions();
        Assert.assertEquals(allSelectedOptions.size(), 3);

        WebElement firstSelectedOption = allSelectedOptions.get(0);
        String firstSelectedOptionText = firstSelectedOption.getText();
        Assert.assertEquals(firstSelectedOptionText, "Black");

        WebElement secondSelectedOptionText = allSelectedOptions.get(1);
        Assert.assertEquals(secondSelectedOptionText.getText(), "Red");

        WebElement thirdSelectedOptionText = allSelectedOptions.get(2);
        Assert.assertEquals(thirdSelectedOptionText.getText(), "Silver");
    }

    @Test
    public void testAllOptions() {
        WebElement makeSelect = driver.findElement(By.name("make"));
        Select make = new Select(makeSelect);

        List<String > allActualOptionsTexts = new ArrayList<>();
        List<WebElement> allActualOptions = make.getOptions();
        for (WebElement actualOption : allActualOptions) {
            allActualOptionsTexts.add(actualOption.getText());
        }

        List<String> expectedActualOptionsTexts = List.of("BMW", "Mercedes", "Audi", "Honda");

        Assert.assertEquals(allActualOptionsTexts, expectedActualOptionsTexts);

    }

}
