package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.PropertiesFile;

public class TestsFromSearchToCheckout {

	static WebDriver driver;
	static WebElement dropdownAll, searchBox, womenTomteamell, btnSearch, priceWomenReebok, size, addToCart, cartCount,
			homeDeals, dreametechProRobot, buttonNoThanks;
	Select select, selectSize;
	static String price26, subtotalOfSunshineTshirt, price320, subtotalVaccumProRobot;

	static SoftAssert softAssert1 = new SoftAssert();
	static SoftAssert softAssert2 = new SoftAssert();

	// @Parameters("browser")
//	public static void launchBrowserAndWebsite(String browser) throws Exception {
//
//		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", PropertiesFile.projectPath + "/drivers/chromedriver.exe");
//			driver = new ChromeDriver();
//			PropertiesFile.getProperties();
//			driver.get(PropertiesFile.url);
//			driver.manage().window().maximize();
//			
//		} else if (browser.equalsIgnoreCase("edge")) {
//			
//			System.setProperty("webdriver.edge.driver", PropertiesFile.projectPath + "/drivers/msedgedriver.exe");
//			driver = new EdgeDriver();
//			PropertiesFile.getProperties();
//			driver.get(PropertiesFile.url);
//			driver.manage().window().maximize();
//		
//		} else {
//			throw new Exception("Incorrect browser");
//		}
//	}

	@BeforeTest
	public void launchBrowserAndWebsite() {

		System.setProperty("webdriver.chrome.driver", PropertiesFile.projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PropertiesFile.getProperties();
		driver.get(PropertiesFile.url);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_1_verifyWomenSunshineTshirtValue() {

		dropdownAll = driver.findElement(By.id("searchDropdownBox"));
		select = new Select(dropdownAll);
		select.selectByValue("search-alias=fashion-womens");

		searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("T-shirts");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		btnSearch = driver.findElement(By.id("nav-search-submit-button"));
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();

		WebDriverWait waitTomtea = new WebDriverWait(driver, Duration.ofSeconds(3));
		womenTomteamell = driver
				.findElement(By.xpath("//img[@src='https://m.media-amazon.com/images/I/61zTy+sU-mL._AC_UL320_.jpg']"));
		waitTomtea.until(ExpectedConditions.elementToBeClickable(womenTomteamell)).click();

		size = driver.findElement(By.name("dropdown_selected_size_name"));
		selectSize = new Select(size);
		selectSize.selectByVisibleText(" Small ");

		WebDriverWait waitaddToCart = new WebDriverWait(driver, Duration.ofSeconds(8));
		addToCart = waitaddToCart
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Add to Shopping Cart']")));
		addToCart.click();

		cartCount = driver.findElement(By.id("nav-cart-count"));
		cartCount.click();

		price26 = driver.findElement(By.className("a-spacing-mini")).getText();

		System.out.println(price26);
		subtotalOfSunshineTshirt = driver.findElement(By.xpath("//div[@data-name='Subtotals']")).getText();

		System.out.println(subtotalOfSunshineTshirt);

		softAssert1.assertEquals(price26, subtotalOfSunshineTshirt);
		softAssert1.assertAll();
	}

	@Test
	public void TC_2_todayDeal() {

		driver.findElement(By.xpath("//a[@href='/ref=nav_logo']")).click();

		driver.findElement(By.xpath("//a[@href='/deals?ref_=nav_cs_gb']")).click();

		homeDeals = driver.findElement(
				By.xpath("//img[@src='https://m.media-amazon.com/images/G/15/Deals_Page/Assets/US/Home_white.png']"));
		homeDeals.click();

		dreametechProRobot = driver.findElement(
				By.xpath("//img[@src='https://m.media-amazon.com/images/I/41ewVm+NRPL._AC_UF226,226_FMjpg_.jpg']"));
		dreametechProRobot.click();

		WebDriverWait waitaddToCart = new WebDriverWait(driver, Duration.ofSeconds(8));
		addToCart = waitaddToCart
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Add to Shopping Cart']")));
		addToCart.click();

		WebDriverWait waitCart = new WebDriverWait(driver, Duration.ofSeconds(10));
		cartCount = driver.findElement(By.id("nav-cart-count"));
		waitCart.until(ExpectedConditions.elementToBeClickable(cartCount)).click();
		
		//cartCount.click();

		price320 = driver.findElement(By.className("a-spacing-mini")).getText();

		subtotalVaccumProRobot = driver.findElement(By.xpath("//div[@data-name='Subtotals']")).getText();

		softAssert2.assertEquals(price320, subtotalVaccumProRobot);
		softAssert2.assertAll();
	}

	@AfterTest
	public void exitBrowser() {
		driver.close();
		driver.quit();
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
