import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


public class SelectTest {

    private WebDriver driver;

    @BeforeMethod
	public void setUp() {
    	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
	}

 	@Test
    public void testDropdown()
    {

 		WebElement dropDown = driver.findElement(By.name("make"));
 		//Get the Dropdown as a Select using it's name attribute
 		Select make = new Select(dropDown);
 		//Verify Dropdown does not support multiple selection

 		assertFalse(make.isMultiple());
 		//Verify Dropdown has four options for selection

    	assertEquals(make.getOptions().size(), 4);

    	//We will verify Dropdown has expected values as listed in a array
    	List<String> exp_options = Arrays.asList(new String[]{"BMW", "Mercedes", "Audi","Honda"});
    	List<String> act_options = new ArrayList<String>();

    	List<WebElement> allOptions = make.getOptions();

    	//Retrieve the option values from Dropdown using getOptions() method
    	for(WebElement option : allOptions) {
			act_options.add(option.getText());
		}

    	//Verify expected options array and actual options array match
    	assertEquals(act_options.toArray(), exp_options.toArray()); // both are the same

    	assertEquals(act_options, exp_options); // both are the same

    	//With Select class we can select an option in Dropdown using Visible Text
    	make.selectByVisibleText("Mercedes");
    	assertEquals(make.getFirstSelectedOption().getText(), "Mercedes");


    	//or we can select an option in Dropdown using value attribute
    	make.selectByValue("audi");
    	assertEquals(make.getFirstSelectedOption().getText(), "Audi");

    	//or we can select an option in Dropdown using index
    	make.selectByIndex(0);
    	assertEquals(make.getFirstSelectedOption().getText(), "BMW");
    }
    
 	@Test
    public void testMultipleSelectList()
    {
 		
 		//Get the List as a Select using it's name attribute
 		Select color = new Select(driver.findElement(By.name("color")));
 		
 		//Verify List support multiple selection
    	assertTrue(color.isMultiple());
    	
    	//Verify List has five options for selection
    	assertEquals(color.getOptions().size(), 5);
    	
    	//Select multiple options in the list using visible text
    	color.selectByVisibleText("Black");
    	color.selectByVisibleText("Red");
    	color.selectByVisibleText("Silver");
    	
    	//Verify there 3 options selected in the list
    	assertEquals(color.getAllSelectedOptions().size(), 3);

    	
    	//We will verify list has multiple options selected as listed in a array
    	List<String> exp_sel_options = Arrays.asList(new String[]{"Black", "Red", "Silver"});
    	List<String> act_sel_options = new ArrayList<String>();
    	    	
    	for(WebElement option : color.getAllSelectedOptions())
    		act_sel_options.add(option.getText());
   	    	
    	//Verify expected array for selected options match with actual options selected
    	assertEquals(act_sel_options.toArray(), exp_sel_options.toArray()); // both rows are the same
    	assertEquals(act_sel_options, exp_sel_options); // both rows are the same

    	//Deselect an option using visible text
    	color.deselectByVisibleText("Silver");
    	//Verify selected options count
    	assertEquals(color.getAllSelectedOptions().size(), 2);
    	
    	//Deselect an option using value attribute of the option
    	color.deselectByValue("rd");
    	//Verify selected options count
    	assertEquals(color.getAllSelectedOptions().size(), 1);
    	
    	//Deselect an option using index of the option
    	color.deselectByIndex(0);
    	//Verify selected options count
    	assertEquals(color.getAllSelectedOptions().size(), 0);
        	
    }
  
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}