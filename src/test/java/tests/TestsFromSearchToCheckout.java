package tests;

import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
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
			price26, allMenu, newReleaeses, rustic, rizone, women, clothing, ul;
	Select select, selectSize;
	static String price, subtotalOfSunshineTshirt;

	static SoftAssert softAssert1 = new SoftAssert();
	static SoftAssert softAssert2 = new SoftAssert();
	static SoftAssert softAssert3 = new SoftAssert();

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
	public void TC_1_womenSunshineTshirtgetValue() throws InterruptedException {

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

		// addToCart = driver.findElement(By.id("add-to-cart-button"));
		WebDriverWait waitaddToCart = new WebDriverWait(driver, Duration.ofSeconds(4));
		waitaddToCart.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
		//addToCart.click();

		cartCount = driver.findElement(By.id("nav-cart-count"));
		cartCount.click();

		price26 = driver.findElement(By.className("a-spacing-mini"));

		subtotalOfSunshineTshirt = driver.findElement(By.xpath("//div[@data-name='Subtotals']"))
				.getText();

		System.out.println(subtotalOfSunshineTshirt);

		softAssert1.assertEquals(subtotalOfSunshineTshirt, "Subtotal (1 item): $25.99");
		softAssert1.assertAll();
	}

	@Test
	public void TC_2_todayDeal() throws InterruptedException {

		allMenu = driver.findElement(By.id("nav-hamburger-menu"));
		HoverAndClick(driver, allMenu, null);

		newReleaeses = driver
				.findElement(By.xpath("//a[@href='/gp/new-releases/?ref_=nav_em_cs_newreleases_0_1_1_3']"));
		newReleaeses.click();

		rustic = driver.findElement(By.xpath(
				"//img[@src='https://images-na.ssl-images-amazon.com/images/I/81oj-A3CYHL._AC_UL450_SR450,320_.jpg']"));
		HoverAndClick(driver, rustic, null);

		rizone = driver.findElement(By.xpath(
				"//a[@href='/s/ref=bl_dp_s_web_0?ie=UTF8&search-type=ss&index=handmade&field-keywords=RIZONE']"));
		HoverAndClick(driver, rizone, null);

		searchBox = driver.findElement(By.xpath("//input[contains(@value,'RIZONE')]"));
		System.out.println(searchBox.getAttribute("value"));

		softAssert2.assertEquals(searchBox.getAttribute("value"), "RIZONE");
		softAssert2.assertAll();
	}
//	
//	@Test
//	public void TC_3_listWomen() throws InterruptedException {
//		

//		
//		allMenu = driver.findElement(By.id("nav-hamburger-menu"));
//		HoverAndClick(driver, allMenu, null);
//
//		ul = driver.findElement(By.xpath("//div[@id='hmenu-content']//ul"));

//		
//		List<WebElement> list = driver.findElements(By.xpath("//div[@id='hmenu-content']/ul/li"));
//		System.out.println(list);
//		for (WebElement clothes : list) {
//			System.out.println(clothes.getText());
//		}

	// softAssert3.assertEquals(,);
	// }

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
