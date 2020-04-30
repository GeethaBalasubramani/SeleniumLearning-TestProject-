import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC8_Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("Webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);

		// 1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");

		// 2) Mouseover on Furniture and click Office Chairs under Chairs
		driver.findElementByXPath("(//a[text()='Furniture'])[1]").click();
		Actions build = new Actions(driver);
		WebElement ele = driver.findElementByXPath("(//div[text()='Chairs'])[1]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		// js.executeScript("arguments[0].click();",ele);
		build.moveToElement(ele).perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		Thread.sleep(2000);
		// 3) click Executive Chairs
		WebElement eleC = driver.findElementByXPath("//img[@alt=\"Executive Chairs\"]");
		js.executeScript("arguments[0].click();", eleC);
		Thread.sleep(2000);
		// 4) Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("//div[@class=\"clip__filter-dimension-input-holder\"]//input[@type='number'][1]")
				.clear();
		System.out.println("Existing value has been cleared");

		driver.findElementByXPath("//div[@class=\"clip__filter-dimension-input-holder\"]//input[@type='number'][1]")
				.sendKeys("50", Keys.ENTER);
		Thread.sleep(2000);

		System.out.println("Entered the value 50");

		// 5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		// driver.findElementByXPath("//a[@data-productname=\"Poise Executive Chair in
		// Black Colour\"]").click();
		driver.findElementByXPath(
				"//a[text()='Poise Executive Chair in Black Colour']//ancestor::div[@class='clip-crd-10x11 pf-white padding-2x1']//a[@id=\"clip_wishlist_\"]")
				.click();

		System.out.println("Chair added to Wishlist");

		// 6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		Thread.sleep(2000);
		build.moveToElement(driver.findElementByXPath("//a[@rel=\"meta-homeware\"]")).perform();
		// build.moveToElement(driver.findElementByXPath("//div[text()='Cookware']")).perform();
		driver.findElementByLinkText("Pressure Cookers").click();

		System.out.println("Clicked Pressure Cookers");

		// 7) Select Prestige as Brand
		driver.findElementByXPath("//div[@class='clip__filter-title']//h2[text()='BRAND          '][1]").click();
		driver.findElementByXPath("//label[@for=\"brandsnamePrestige\"][1]").click();

		System.out.println("Clicked Prestige");
		// 8)Select Capacity as 1-3 Ltr
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@for=\"capacity_db1_Ltr_-_3_Ltr\"]//parent::*").click();

		System.out.println("Capacity as 1-3 Ltr");

		// 9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(2000);
		driver.findElementByXPath(
				"//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']//ancestor :: div[@class=\"clip-crd-10x11 pf-white padding-2x1\"]//a[@data-tooltip=\"Add to Wishlist\"]")
				.click();

		System.out.println("Clicked Nakshatra Metallic Red Aluminium Cooker 2 Ltr");

		// 10) Verify the number of items in Wishlist

		String text = driver.findElementByXPath("//div[@class=\"wishlist_bar\"]//span[text()='2']").getText();

		String replaceAll = text.replaceAll("\\D", "");

		int wishListCount = Integer.parseInt(replaceAll);

		System.out.println("Verify Items in WishList:" + wishListCount);
		Thread.sleep(2000);
		// 11) Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip=\"Wishlist\"]").click();

		// 12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath("(//a[@data-tooltip=\"Add to Cart\"])[2]").click();

		System.out.println("Pressure Cooker added to Cart");

		// 13) Check for the availability for Pincode 600128
		driver.findElementByXPath("//input[@class=\"srvc_pin_text\"]").sendKeys("600128", Keys.ENTER);
		driver.findElementByLinkText("Check").click();

		System.out.println("Successfully Checked Pincode");

		// 14) Click Proceed to Pay Securely
		Thread.sleep(2000);
		driver.findElementByXPath("//a[contains(text(),'Proceed to pay')]").click();

		System.out.println("Clicked Proceed to pay");

		// 15 Click Proceed to Pay

		driver.findElementByXPath("//a[text()='PLACE ORDER']").click();

		System.out.println("Order has been placed");

		driver.findElementByXPath("//span[text()='ORDER SUMMARY']").click();
		Thread.sleep(2000);

		// 16) Capture the screenshot of the item under Order Item

		File src = driver.findElementByXPath("//div[@role=\"listbox\"]//li").getScreenshotAs(OutputType.FILE);

		File DestFile = new File("./img/image.jpg");

		FileUtils.copyFile(src, DestFile);

		System.out.println("Screenshot has been taken");

		// 17) Close the browser

		driver.close();

	}

}
