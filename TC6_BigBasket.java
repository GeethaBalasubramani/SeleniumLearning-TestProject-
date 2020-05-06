package learningTestCase;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC6_BigBasket {

	public static void main(String[] args) throws InterruptedException {

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
		String url = "https://www.bigbasket.com/";

		// 1) Go to https://www.bigbasket.com/
		driver.get(url);
		// 2) mouse over on Shop by Category
		Thread.sleep(3000);
		Actions build = new Actions(driver);
		driver.findElementByXPath("(//a[@data-toggle=\"dropdown\"])[3]").click();
		Thread.sleep(3000);
		// 3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		build.moveToElement(driver.findElementByLinkText("Foodgrains, Oil & Masala")).perform();
		build.moveToElement(driver.findElementByLinkText("Rice & Rice Products")).perform();

		// 4) Click on Boiled & Steam Rice
		driver.findElementByLinkText("Boiled & Steam Rice").click();
		Thread.sleep(3000);
		// 5) Choose the Brand as bb Royal
		driver.findElementByXPath("(//span[text()='Brand'])[1]").click();
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
		// 6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		Thread.sleep(3000);
		System.out.println(
				driver.findElementByXPath("//a[@uib-tooltip=\"Ponni Boiled Rice - Super Premium\"]").getText());
		WebElement ele = driver.findElementByXPath("(//div[@class='col-sm-12 col-xs-7 qnty-selection'])[3]");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(3000);
		// build.moveToElement(driver.findElementByXPath("//span[text()='5 kg - Rs
		// 336.00 ']")).click().perform();
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElementByXPath("(//span[@data-bind='label' or text()='5 kg  - Rs 336.00'])[3]")));
		WebElement element = driver.findElementByXPath("(//span[@data-bind='label' or text()='5 kg  - Rs 336.00'])[3]");
		build.moveToElement(element).click().perform();
		System.out.println("5Kg Clicked");
		Thread.sleep(3000);
		// 7) print the price of Rice

		String price = driver.findElementByXPath("//span[text()='336']").getText();
		System.out.println("Print the price of the Rice:" + price);

		// 8) Click Add button
		driver.findElementByXPath("(//button[text()='Add '])[3]").click();
		// driver.findElement(By.xpath("//body")).click();
		// 9) Verify the success message displayed
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class=\"Bob6r\"]")));
		String successMsg = driver.findElementByXPath("//div[contains(text(),'Successfully added Ponni Boiled Rice')]")
				.getText();
		System.out.println("Verify the Success Message Displayed:" + successMsg);
		// driver.findElementByXPath("//a[text()='Continue'])[1]").click();
		// 10) Type Dal in Search field and enter
		driver.findElementByXPath("(//input[@placeholder=\"Search for Products..\"])[1]").sendKeys("Dal", Keys.TAB);
		// driver.findElementByXPath("(//a[text()='Continue'])[1]").click();

		// 12) Go to Toor/Arhar Dal and select 2kg & set Qty 2

		// driver.findElementByXPath("(//p[text()='Toor/Arhar '])[1]").click();
		Thread.sleep(3000);

		WebElement Dal = driver.findElementByXPath("(//i[@class='fa fa-caret-down'])[2]");
		js.executeScript("arguments[0].click();", Dal);

		WebElement Qty = driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]");
		Qty.sendKeys("2");

		WebElement dal2Kg = driver.findElementByXPath("(//a[@ng-click='vm.onProductChange(allProducts)'])[6]");
		Thread.sleep(2000);
		build.moveToElement(dal2Kg).click().perform();

		// find all the elements with that xpath match
		// List<WebElement> elements = driver.findElements(By.xpath(xpath));
		// iterate over the elements
		// for (WebElement webElement : elements) {
		// check whether element is displayed or not,
		// if (webElement.isDisplayed()) {
		// if displayed click the element and break the loop.
		// webElement.click();
		// break;

		// driver.findElementByXPath("(//input[@ng-model=\"vm.startQuantity\"])[1]").sendKeys("2",Keys.ENTER);
		// driver.findElementByXPath("(//span[contains(text(),'2 kg
		// Pouch')])[3]").click();
		// 13) Print the price of Dal
		String dalPrice = driver.findElementByXPath("//span[text()='219']").getText();
		System.out.println("Print the price of Dal:" + dalPrice);
		// 14) Click Add button
		driver.findElementByXPath("(//button[@class=\"btn btn-add col-xs-9\"])[3]").click();
		// 15) Mouse hover on My Basket
		build.moveToElement(driver.findElementByXPath("//span[@class=\"basket-content\"]")).perform();
		// 16) Validate the Sub Total displayed for the selected items
		String subTotal = driver.findElementByXPath("//p[text()='Sub Total : ']").getText();
		System.out.println("Sub Toatal:" + subTotal);
		// 17) Reduce the Quantity of Dal as 1
		driver.findElementByXPath("(//button[@ng-click='vm.decreamentQuantity(cartItem);'])[2]").click();
		// 18) Validate the Sub Total for the current items
		String subTotalfor1 = driver.findElementByXPath("//p[text()='Sub Total : ']").getText();
		System.out.println("SubTotal:" + subTotalfor1);
		// 19) Close the Browser
		driver.close();

	}

}
