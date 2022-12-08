package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.PropertiesFile;

public class TCs_Search {

	static WebDriver driver;

	SoftAssert softAssert1 = new SoftAssert();

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

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

	@AfterTest
	public void exitBrowser() {
		driver.close();
		driver.quit();
	}
}
