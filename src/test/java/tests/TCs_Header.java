package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.PropertiesFile;

public class TCs_Header {
	static WebDriver driver;
	SoftAssert softAssert1 = new SoftAssert();
	SoftAssert softAssert2 = new SoftAssert();
	SoftAssert softAssert3 = new SoftAssert();
	SoftAssert softAssert4 = new SoftAssert();

	@BeforeTest
	public void initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", PropertiesFile.projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		PropertiesFile.getProperties();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test
	public void TC_1_getHeaderList() {
		driver.get(PropertiesFile.url);

		List<WebElement> headerList = driver.findElements(By.xpath("//div[@id='nav-xshop']/a"));
		System.out.println(headerList.size());

		for (WebElement header : headerList) {
			System.out.println(header.getText());
		}

		softAssert1.assertEquals(headerList.size(), 28);
		softAssert1.assertAll();
	}

	@Test
	public void TC_2_getAllMenuList() {
		driver.get(PropertiesFile.url);

		List<WebElement> allDropdownList = driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
		System.out.println(allDropdownList.size());

		for (WebElement oneEle : allDropdownList) {
			System.out.println(oneEle.getText());
		}

		softAssert2.assertTrue(allDropdownList.size() == 40);
		softAssert2.assertAll();
	}

	@Test
	public void TC_3_selectFromAllList() {
		driver.get(PropertiesFile.url);

		List<WebElement> allDropdownList = driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
		System.out.println(allDropdownList.size());

		WebElement All = driver.findElement(By.id("searchDropdownBox"));
		All.click();

		Select s = new Select(All);
		s.selectByVisibleText("Books");
		s.selectByIndex(8);

		for (WebElement one : allDropdownList) {
			if (one.isSelected() == true) {
				System.out.println(one.getText() + "-----" + one.isSelected());
				softAssert3.assertEquals(one.getText(), "Beauty");
				softAssert3.assertAll();
			}
		}
	}

	@AfterTest
	public void exitBrowser() {
		driver.close();
		driver.quit();
	}
}
