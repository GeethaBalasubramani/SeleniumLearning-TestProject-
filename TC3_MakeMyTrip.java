package learningTestCase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC3_MakeMyTrip {

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

		// 1) Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com");

		// 2) Click Hotels
		driver.findElementByXPath("//li[@class='menu_Hotels']").click();

		// 3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//span[text()='City / Hotel / Area / Building']").click();
		Thread.sleep(5000);
		// Click City
		driver.findElementByXPath("//input[contains(@placeholder,'Enter city')]").sendKeys("Goa", Keys.ENTER);
		driver.findElementByXPath("//p[text()='Goa, India']").click();

		Thread.sleep(3000);
		// 4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		// String inDate = "May 15 2020";
		// String outDate = "May 19 2020";

		driver.findElementById("checkin").click();
		driver.findElementByXPath("//div[@aria-label=\"Fri May 15 2020\"]").click();
		driver.findElementByXPath("//div[@aria-label=\"Tue May 19 2020\"]").click();

		// 5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy=\"adults-2\"]").click();
		driver.findElementByXPath("//li[@data-cy=\"children-1\"]").click();

		// Apply Button.
		driver.findElementByXPath("//button[@class=\"primaryBtn btnApply\"]").click();

		// 6) Click Search button
		driver.findElementById("hsw_search_button").click();

		// 7) Select locality as Baga

		WebElement element = driver.findElement(By.xpath("//span[text()='Search Location']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		WebElement ele = driver.findElementByXPath("//label[@for=\"mmLocality_checkbox_35\"]");
		executor.executeScript("arguments[0].click();", ele);

		driver.findElementByXPath("//span[@class=\"mmClose\"]").click();
		driver.findElement(By.xpath("//body")).click();

		// 8) Select 5 start in Star Category under Select Filters
		WebElement ele1 = driver.findElementByXPath("(//input[@type=\"checkbox\"])[13]");
		executor.executeScript("arguments[0].click();", ele1);

		// 9) Click on the first resulting hotel and go to the new window
		WebElement ele2 = driver.findElementByXPath("(//div[@class='listingRowOuter'])[1]");
		executor.executeScript("arguments[0].click();", ele2);
		Thread.sleep(3000);
		driver.findElementByXPath("//p[@id=\"hlistpg_hotel_name\"][1]").click();
		Set<String> Window = driver.getWindowHandles();
		List<String> WinList = new ArrayList<String>(Window);
		Thread.sleep(3000);
		driver.switchTo().window(WinList.get(1));
		String PageName = driver.getTitle();
		System.out.println(PageName);

		Thread.sleep(3000);
		// 10) Print the Hotel Name
		String hotelName = driver.findElementByXPath("//span[text()='The Park Baga River Goa']").getText();
		System.out.println("Print the Hotel Name:" + hotelName);
		Thread.sleep(3000);
		// 11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("(//span[contains(text(),'MORE OPTIONS')])[1]").click();
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		driver.findElementByClassName("close").click();
		// 12) Click on BOOK THIS NOW
		driver.findElement(By.id("detpg_headerright_book_now")).click();
		// 13) Print the Total Payable amount
		String totalPayAmt = driver.findElement(By.id("revpg_total_payable_amt")).getText();
		System.out.println("TOTAL NEED TO BE PAIED : " + totalPayAmt);

		// 14) Close the browser
		driver.quit();

	}

}
