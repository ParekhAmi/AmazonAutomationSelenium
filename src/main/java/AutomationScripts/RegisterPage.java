package AutomationScripts;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class RegisterPage {
	static WebDriver driver;
	static WebElement dropdownAll;
	Select select;

	public void TC_1_clickSignIn() throws InterruptedException {
	
		
	
		dropdownAll = driver.findElement(By.id("searchDropdownBox"));
	    select = new Select(dropdownAll);
		select.selectByVisibleText("Women");
	}
	
	
	public static void Hover(WebDriver driver, WebElement elementToHover) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).perform();
	}

	public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
		if (elementToClick != null) {
			Actions action = new Actions(driver);
			action.moveToElement(elementToHover).click(elementToClick).build().perform();
		} else {
			Actions action = new Actions(driver);
			action.moveToElement(elementToHover).click().build().perform();
		}

	}
}
