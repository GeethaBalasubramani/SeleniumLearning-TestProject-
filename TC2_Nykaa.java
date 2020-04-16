package learningTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC2_Nykaa {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		// 1) Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		// 2) Mouseover on Brands and Mouseover on Popular
		WebElement eleMove = driver.findElementByXPath("//a[text()='brands']");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleMove).perform();
		builder.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).perform();

		// 3)Click L'Oreal Paris
		driver.findElementByXPath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']")
				.click();

		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> WindowsHandle1 = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(WindowsHandle1);
		driver.switchTo().window(windowList.get(1));
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		// 5)Click sort By and select customer top rated
		driver.findElementByXPath("//span[text()='Sort By : ']").click();
		driver.findElementByXPath("(//span[text()='customer top rated'])[1]").click();
		Thread.sleep(3000);
		// 6)Click Category and click Shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("(//span[contains(text(),'Shampoo')])[1]").click();

		// 7) check whether the Filter is applied with Shampoo
		String filterCheck = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if (filterCheck.contains("Shampoo")) {
			System.out.println("Filter Applied Correctly.");
		} else {
			System.out.println("Filters not applied with Shampoo.");
		}

		// 8)Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElementByXPath("//span[contains(text(),'Oreal Paris Colour Protect Shampoo')]").click();

		// 9) GO to the new window and select size as 175ml
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> secondWinList = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(secondWinList.get(2));
		driver.findElementByXPath("(//span[@class='size-pallets'])[2]").click();

		// 10) Print the MRP of the product
		String priceMRP = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println("MRP of the Product:" + priceMRP);

		// 11) Click on ADD to BAG
		driver.findElementByXPath("(//div[@class='pull-left'])[1]").click();

		// 12) Go to Shopping Bag
		driver.findElementByXPath("//div[@class='AddToBagbox']").click();

		// 13) Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("//div[@class='value medium-strong']").getText();
		System.out.println("The Grand Total Amount:" + grandTotal);

		// 14) Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();

		// 15) Click on Continue as Guest
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();

		// 16) Print the warning message (delay in shipment)
		WebElement warningMessage = driver
				.findElementByXPath("//div[contains(@class,'layout horizontal p10 communication')]");
		System.out.println("Print the Warning Message:" + warningMessage.getText());

		// 17) Close all windows
		driver.quit();

	}

}
