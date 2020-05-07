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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC15_ajio {

	private static String CouponCodeEpic;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		Actions act = new Actions(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1)Go to https://www.ajio.com/
		driver.get("https://www.ajio.com/shop/sale");
		// 2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@placeholder=\"Search AJIO\"]").sendKeys("Bags");
		driver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();
		// 3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//label[text()='Grid ']").click();
		WebElement dp = driver.findElementByXPath("//div[@class=\"filter-dropdown\"]/select");
		Select ele = new Select(dp);
		ele.selectByVisibleText("What's New");
		// 4) Enter Price Range Min as 2000 and Max as 5000
		Thread.sleep(2000);
		WebElement price = driver.findElementByXPath("//span[text()='price']/preceding-sibling::span");
		Thread.sleep(500);
		js.executeScript("arguments[0].click();", price);
		driver.findElementById("minPrice").sendKeys("2000");
		driver.findElementById("maxPrice").sendKeys("5000", Keys.ENTER);
		// 5) Click on the product "Puma Ferrari LS Shoulder Bag"
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class=\"contentHolder\"]//div[text()='Ferrari LS Shoulder Bag']").click();

		Set<String> winset = driver.getWindowHandles();
		List<String> winLis = new ArrayList<String>(winset);
		driver.switchTo().window(winLis.get(1));
		System.out.println(driver.getTitle());
		// 6) Verify the Coupon code for the price above 2690 is applicable for your
		// product,
		Thread.sleep(2000);
		String promoPrice = driver.findElementByXPath("//div[text()='Rs. 2,974']").getText();
		String disPrice = promoPrice.replaceAll("\\D", "");// -String
		int FinalPrice = Integer.parseInt(disPrice);// --Integer
		System.out.println("Print the the Finalprice:" + FinalPrice);// 2974
		// if applicable the get the Coupon Code and
		// driver.findElementByXPath("//div[text()='Use Code']/br");
		// driver.findElementByXPath("//div[text()='EPIC']"
		if (FinalPrice >= 2690) {
			System.out.println("Coupon is applicable");

			String WCouponCode = driver.findElementByXPath("//div[@class=\"promo-title\"]").getText();
			CouponCodeEpic = WCouponCode.substring(8);
			System.out.println("CouponCode:" + CouponCodeEpic);

		} else {
			System.out.println("Coupon is not applicable");
		}
		// Calculate the discount price for the coupon
		String offPrice = driver.findElementByXPath("//div[text()='Get it for ']/span").getText();
		String st = offPrice.replaceAll("\\D", "");
		int offerPrice = Integer.parseInt(st);
		System.out.println("Calculate Discount Price:" + offerPrice);// 2141
		int DiscountPrice = FinalPrice - offerPrice; // 2974-2141 =833
		System.out.println("Calculate Discount price of Coupon:" + DiscountPrice);// 833

		// 7) Check the availability of the product for pincode 560043/682001,
		Thread.sleep(2000);
		driver.findElementByClassName("edd-pincode-msg-container").click();
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("682001");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
		// print the expected delivery date if it is available
		String Dildate = driver.findElementByClassName("edd-message-success-details").getText();
		System.out.println("Print the Delivery date:" + Dildate);

		// 8) Click on Other Informations under Product Details and
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Other information']").click();
		// Print the Customer Care address, phone and email
		String CustomCare = driver.findElementByXPath("//span[text()='Customer Care Address']/parent::*").getText();
		System.out.println("Print the Customer care Details:" + CustomCare);

		// 9) Click on ADD TO BAG and then GO TO BAG
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class=\"btn-gold\"]/span").click();
		WebElement button = driver.findElementByXPath("//span[text()='GO TO BAG']");
		js.executeScript("arguments[0].click();", button);
		// 10) Check the Order Total before apply coupon
		Thread.sleep(2000);
		String order = driver.findElementByXPath("//span[@class=\"price-value bold-font\"]").getText();
		System.out.println("OrderTotal:" + order);// ResultOrderTotal:Rs. 2,974.00

		String Total = order.replaceAll("\\D", "");
		int orderTotal = Integer.parseInt(Total);
		System.out.println("Check the Total Order:" + orderTotal);// Check the Total Order:297400
		// 11) Enter Coupon Code and Click Apply
		Thread.sleep(2000);
		driver.findElementById("couponCodeInput").sendKeys(CouponCodeEpic);
		driver.findElementByXPath("//button[text()='Apply']").click();
		// 12) Verify the Coupon Savings amount(round off if it in decimal) under Order
		// Summary and
		// the matches the amount calculated in Product details
		Thread.sleep(3000);
		String savingAmount = driver.findElementByXPath("//span[text()='Rs. 832.72']").getText();
		System.out.println("Verify Saving Amount:" + savingAmount);// Rs. 832.72
		String st1 = savingAmount.replaceAll("Rs.", "");
		System.out.println(st1);// 832.72
		Thread.sleep(500);
		// savingAmount = savingAmount.substring(5);
		// System.out.println(savingAmount);
		float f = Float.parseFloat(st1);
		System.out.println(f);// 832.72
		int roundoff = Math.round(f);
		System.out.println(roundoff);// 833
		if (roundoff == DiscountPrice) {
			System.out.println("Discount price matches");
		} else {
			System.out.println("Discount price does not match");

		}
		// 13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[text()='Delete']").click();
		// 14) Close all the browsers
		driver.quit();

	}
}
