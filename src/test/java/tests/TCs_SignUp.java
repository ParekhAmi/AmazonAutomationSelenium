package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.PropertiesFile;

public class TCs_SignUp {

	static WebDriver driver;
	SoftAssert softAssert1 = new SoftAssert();
	SoftAssert softAssert2 = new SoftAssert();
	SoftAssert softAssert3 = new SoftAssert();
	SoftAssert softAssert4 = new SoftAssert();
	SoftAssert softAssert5 = new SoftAssert();
	SoftAssert softAssert6 = new SoftAssert();

	@BeforeTest
	public void initializeBrowser() {
		System.setProperty("webdriver.chrome.driver", PropertiesFile.projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		PropertiesFile.getProperties();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(groups = {"SignUp"})
	public void TC_1_emptyFirstAndLastName() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_email")).sendKeys("weren@yahoo.com");
		driver.findElement(By.id("ap_password")).sendKeys("123456");
		driver.findElement(By.id("ap_password_check")).sendKeys("123456");
		driver.findElement(By.id("continue")).click();

		String expectedError = "Enter your name";
		String actualError = driver.findElement(By.xpath("//div[contains(text(),'Enter your name')]")).getText();
		System.out.println(actualError);

		softAssert1.assertEquals(actualError, expectedError);
		softAssert1.assertAll();
	}

	@Test(groups = {"SignUp"})
	public void TC_2_emptyMobileOrEmail() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_customer_name")).sendKeys("Weren Painter");
		driver.findElement(By.id("ap_password")).sendKeys("123456");
		driver.findElement(By.id("ap_password_check")).sendKeys("123456");
		driver.findElement(By.id("continue")).click();

		String expectedError = "Enter your e-mail address or mobile phone number";
		String actualError = driver
				.findElement(By.xpath("//div[contains(text(),'Enter your e-mail address or mobile phone number')]"))
				.getText();
		System.out.println(actualError);

		softAssert2.assertEquals(actualError, expectedError);
		softAssert2.assertAll();
	}

	@Test(groups = {"SignUp"})
	public void TC_3_emptyPassword() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_customer_name")).sendKeys("Weren Painter");
		driver.findElement(By.id("ap_email")).sendKeys("weren@yahoo.com");
		driver.findElement(By.id("ap_password_check")).sendKeys("123456");
		driver.findElement(By.id("continue")).click();

		String expectedError = "Minimum 6 characters required";
		String actualError = driver
				.findElement(By.xpath("(//div[contains(text(),'Minimum 6 characters required')])[1]")).getText();
		System.out.println(actualError);

		softAssert3.assertEquals(actualError, expectedError);
		softAssert3.assertAll();
	}

	@Test(groups = {"SignUp"})
	public void TC_4_emptyPasswordAgain() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_customer_name")).sendKeys("Weren Painter");
		driver.findElement(By.id("ap_email")).sendKeys("weren@yahoo.com");
		driver.findElement(By.id("ap_password")).sendKeys("123456");
		driver.findElement(By.id("continue")).click();

		String expectedError = "Type your password again";
		String actualError = driver.findElement(By.xpath("//div[contains(text(),'Type your password again')]"))
				.getText();
		System.out.println(actualError);

		softAssert4.assertEquals(actualError, expectedError);
		softAssert4.assertAll();
	}

	@Test(groups = {"SignUp"})
	public void TC_5_wrongPasswordAgain() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_customer_name")).sendKeys("Weren Painter");
		driver.findElement(By.id("ap_email")).sendKeys("weren@yahoo.com");
		driver.findElement(By.id("ap_password")).sendKeys("123456");
		driver.findElement(By.id("ap_password_check")).sendKeys("123");

		driver.findElement(By.id("continue")).click();

		String expectedError = "Passwords do not match";
		String actualError = driver.findElement(By.xpath("//div[contains(text(),'Passwords do not match')]")).getText();
		System.out.println(actualError);

		softAssert5.assertEquals(actualError, expectedError);
		softAssert5.assertAll();
	}

	@Test(groups = {"SignUp"})
	public void TC_6_invalidEmail() {

		driver.get(PropertiesFile.url);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("nav-line-1-container"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();

		driver.findElement(By.id("ap_customer_name")).sendKeys("Weren Painter");
		driver.findElement(By.id("ap_email")).sendKeys("werenyahocom");
		driver.findElement(By.id("ap_password")).sendKeys("123456");
		driver.findElement(By.id("ap_password_check")).sendKeys("123456");

		driver.findElement(By.id("continue")).click();

		String expectedError = "Wrong or invalid e-mail address or mobile phone number. Please correct it and try again.";
		String actualError = driver.findElement(By.xpath("(//div[@role='alert']/div/div)[3]")).getText();
		System.out.println(actualError);

		softAssert6.assertEquals(actualError, expectedError);
		softAssert6.assertAll();
	}

	@AfterTest
	public void exitBrowser() {
		driver.close();
		driver.quit();
	}
}
