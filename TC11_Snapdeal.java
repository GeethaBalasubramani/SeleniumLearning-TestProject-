package learningTestCase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC11_Snapdeal {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		Actions act = new Actions(driver);

		// 1) Go to https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		// ‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		driver.findElementByXPath("//span[contains(text(),\"Toys, Kids' \")]").click();
		act.moveToElement(driver.findElementByXPath("//span[text()='Toys']")).perform();

		// 3) Click Educational Toys in Toys & Games
		driver.findElementByXPath("//span[text()='Educational Toys']").click();

		System.out.println("Clicked Educational Toys in toys & Game");
		// ‎4) Click the Customer Rating 4 star and Up
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class=\"filter-type-name lfloat\"])[4]").click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver
				.findElementByXPath("(//div[@class=\"filters-list sdRadio  \"])//label[@for=\"avgRating-4.0\"]");
		js.executeScript("arguments[0].click();", element);
		System.out.println("Clicked Customer Rating 4 star");

		// 5) Click the offer as 40-50
		Thread.sleep(2000);

		WebElement ele = driver.findElementByXPath("(//div[@class=\"filter-type-name lfloat\"])[3]");
		js.executeScript("arguments[0].click();", ele);
		WebElement ele1 = driver
				.findElementByXPath("//div[@class=\"sdCheckbox filters-list \"]//label[@for=\"discount-40%20-%2050\"]");
		js.executeScript("arguments[0].click();", ele1);

		System.out.println("Clicked the offer as 40-50 discount");
		// 6) Check the availability for the pincode
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder=\"Enter your pincode\"]").sendKeys("632602", Keys.TAB);
		WebElement ele2 = driver.findElementByXPath("//button[@class=\"pincode-check\"]");
		js.executeScript("arguments[0].click();", ele2);
		System.out.println("Checked availability for the pincode");

		Thread.sleep(2000);
		// 7) Click the Quick View of the first product
		WebElement ele3 = driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]");
		js.executeScript("arguments[0].click();", ele3);
		// 8) Click on View Details
		WebElement ele4 = driver.findElementByXPath("//a[@class=\" btn btn-theme-secondary prodDetailBtn\"]");
		js.executeScript("arguments[0].click();", ele4);

		// 9) Capture the Price of the Product and Delivery Charge
		Thread.sleep(2000);
		String priceofProduct = driver.findElementByXPath("//span[text()='439']").getText();
		System.out.println("Capture the price of the product:" + priceofProduct);

		int Pprice = Integer.parseInt(priceofProduct);

		String deliveryCharge = driver.findElementByXPath("(//span[@class=\"availCharges\"])[2]").getText();
		System.out.println("Caputer the DeliveryCharges:" + deliveryCharge);

		int Dcharge = Integer.parseInt(deliveryCharge.replaceAll("\\D", ""));

		int TotalPrice = Pprice + Dcharge;
		System.out.println("TotalPrice:" + TotalPrice);
		Thread.sleep(2000);
		driver.findElementById("add-cart-button-id").click();

		// 10) Validate the You Pay amount matches the sum of (price+deliver charge)
		String Amount = driver.findElementByClassName("you-pay").getText();
		int TotalAmount = Integer.parseInt(Amount.replaceAll("\\D", ""));
		System.out.println("Total Pay Amount:" + TotalAmount);

		if (TotalPrice == TotalAmount) {
			System.out.println("Payable amount matches the delivery charge");
		} else {
			System.out.println("Payable amount does not matches");
		}
		Thread.sleep(2000);
		// 11) Search for Sanitizer
		driver.findElementById("inputValEnter").sendKeys("Sanitizer", Keys.ENTER);
		driver.findElementByClassName("searchTextSpan").click();

		// 12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		Thread.sleep(2000);
		driver.findElementByXPath("//p[contains(@title,\"BioAyurveda Neem Power \")]").click();
		Set<String> winSet = driver.getWindowHandles();
		List<String> winlis = new ArrayList<String>(winSet);
		driver.switchTo().window(winlis.get(1));
		driver.getTitle();

		// 13) Capture the Price and Delivery Charge
		String price = driver.findElementByXPath("//span[text()='247']").getText();
		System.out.println("Capture the price:" + price);

		int PC = Integer.parseInt(price);

		String availCharge = driver.findElementByXPath("(//span[@class=\"availCharges\"])[2]").getText();
		int AC = Integer.parseInt(availCharge.replaceAll("\\D", ""));
		System.out.println("Caputer the DeliveryCharge:" + AC);

		int Total = PC + AC;
		System.out.println("Capture the price and Delivery Charge:" + Total);
		Thread.sleep(2000);
		// 14) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
		System.out.println("Clicked on Add to Cart");
		Thread.sleep(1000);
		// 15) Click on Cart
		driver.findElementByXPath("//span[text()='Cart']").click();
		System.out.println("Clicked on Cart");

		// 16) Validate the Proceed to Pay matches the total amount of both the products
		Thread.sleep(3000);
		// String TAmount = driver.findElementById("checkout-continue").getText();
		int TotalFinalprice = Integer.parseInt(
				driver.findElementByXPath("//input[@type='button']").getAttribute("value").replaceAll("\\D", ""));
		System.out.println("Finalprice of produts" + TotalFinalprice);

		if (TotalFinalprice == TotalPrice + Total) {

			System.out.println("Payable amount matches the grand total amount of both products");
		} else {
			System.out.println("Payable amount doesn't matches the grand toatal amount of both products");
		}

		// 17) Close all the windows
		driver.quit();
	}

}
