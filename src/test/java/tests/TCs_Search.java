package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.PropertiesFile;

public class TCs_Search {

	static WebDriver driver;

	SoftAssert softAssert1 = new SoftAssert();
	SoftAssert softAssert2 = new SoftAssert();

	@BeforeTest
	public void initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", PropertiesFile.projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		PropertiesFile.getProperties();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test
	public void TC_1_searchPerfume() throws IOException {
		driver.get(PropertiesFile.url);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

		WebElement All = driver.findElement(By.id("searchDropdownBox"));

		Select s = new Select(All);
		s.selectByIndex(8);

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Perfume for women" + Keys.ENTER);

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(0,4000)", " ");

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[contains(text(),'Body Fantasies 4-Piece Gift Set, 50 ml (Pack of 4)')]"))).click();

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(PropertiesFile.projectPath + "/screenshots/perfume.png"));

		String expecErr = "Body Fantasies";
		String actErr = driver.findElement(By.xpath("//tr[contains(@class,'po-brand')]/td[2]/span")).getText();
		System.out.println(actErr);

		softAssert1.assertEquals(actErr, expecErr);
		softAssert1.assertAll();
	}

	@Test
	public void TC_2_searchSizeOfCoffeeMakerTypes() {
		driver.get(PropertiesFile.url);

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("coffee maker");
		List<WebElement> cofeeMakerTypes = driver
				.findElements(By.xpath("//div[@class='cards_carousel_widget-sug-container-top']//a"));
		System.out.println(cofeeMakerTypes.size());

		for (WebElement coffeeMakerType : cofeeMakerTypes) {
			System.out.println(coffeeMakerType.getText());
		}

		softAssert2.assertEquals(cofeeMakerTypes.size(), 6);
		softAssert2.assertAll();
	}

	@Test
	public void TC_3_flowerPotFromPatioMenu() {
		driver.get(PropertiesFile.url);
		
		Select s = new Select(driver.findElement(By.id("searchDropdownBox")));
		s.selectByValue("search-alias=lawngarden");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("flower pots"+ Keys.ENTER);
		
		
		WebElement flowerPot = driver.findElement(By.xpath("//img[@data-image-index='14']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", flowerPot);
		
		flowerPot.click();
		String actualTitle = driver.findElement(By.xpath("//h1[@id='title']/span")).getText();
		String expecTitle = "Plant Pots, 3 Packs 8 inch Planters with Drainage Hole Saucer, Plastic Flower Pots for Indoor Plants Retro Decorative for Outdoor Garden Container Sets(Copper-8 inch)";
		
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expecTitle);
	}

	@AfterTest
	public void exitBrowser() {
		driver.close();
		driver.quit();
	}
}
