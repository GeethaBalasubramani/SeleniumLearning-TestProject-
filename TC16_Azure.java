package learningTestCase;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC16_Azure {

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
		String url = "https://azure.microsoft.com/en-in/";

		// 1) Go to https://azure.microsoft.com/en-in/
		driver.navigate().to(url);
		// 2) Click on Pricing
		Thread.sleep(2000);
		driver.findElementByLinkText("Pricing").click();
		// 3) Click on Pricing Calculator
		driver.findElementByXPath("//a[contains(text(),' Pricing calculator')]").click();
		// 4) Click on Containers
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@value=\"containers\"]").click();
		// 5) Select Container Instances
		driver.findElementByXPath("(//button[@title=\"Container Instances\"])[2]").click();
		// 6) Click on Container Instance Added View
		Thread.sleep(2000);
		driver.findElementById("new-module-loc").click();
		// 7) Select Region as "South India"
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@aria-label='Region']")));
		WebElement region = driver.findElementByXPath("//select[@aria-label='Region']");
		Select sel = new Select(region);
		sel.selectByVisibleText("South India");
		// 8) Set the Duration as 180000 seconds
		WebElement seconds = driver.findElementByXPath("(//input[@aria-label=\"Seconds\"])[1]");
		seconds.sendKeys(Keys.DELETE);
		seconds.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), "180000");
		// 9) Select the Memory as 4GB
		WebElement memory = driver.findElementByXPath("(//select[@aria-label=\"Memory\"])[1]");
		Select sel_Memory = new Select(memory);
		sel_Memory.selectByVisibleText("4 GB");
		// 10) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//span[text()='Dev/Test pricing has been applied']")));
		// 11) Select Indian Rupee as currency
		WebElement currency = driver.findElementByXPath("(//select[@aria-label=\"Currency\"])[1]");
		Select sel_currency = new Select(currency);
		sel_currency.selectByVisibleText("Indian Rupee (₹)");
		System.out.println("Selected Indian Rupee ");
		// 12) Print the Estimated monthly price
		String ele_Price = driver.findElementByXPath("(//span[@class=\"numeric\"])[4]").getText();
		System.out.println("print the Estimated monthly price:" + ele_Price);
		// 13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[@class=\"calculator-button button-transparent export-button\"]").click();
		// 14) Verify the downloded file in the local folder
		File xl = new File("C:\\Users\\DELL\\Downloads\\ExportedEstimate.xlsx");
		if (xl.exists()) {
			System.out.println("xl is Exist");
		} else {
			System.out.println("xl doesnot Exist");
		}

		// 15) Navigate to Example Scenarios and Select CI/CD for Containers
		act.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();

		Thread.sleep(1000);
		driver.findElementByXPath("//a[text()='Example Scenarios']").click();

		Thread.sleep(2000);

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@title='CI/CD for Containers']")));

		driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();

		// 16) Click Add to Estimate
		Thread.sleep(3000);

		WebElement button = driver.findElementByXPath("//button[text()='Add to estimate']");
		js.executeScript("arguments[0].click()", button);

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Estimate added!']")));

		// 17) Change the Currency as Indian Rupee
		Thread.sleep(2000);
		WebElement currency_1 = driver.findElementByXPath("//select[@class=\"select currency-dropdown\"]");
		Select Sel_1Currency = new Select(currency_1);
		Sel_1Currency.selectByValue("INR");

		// 18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();

		// 19) Export the Estimate
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Export']").click();

		// 20) Verify the downloded file in the local folder
		File xl_1 = new File("C:\\Users\\DELL\\Downloads\\ExportedEstimate.xlsx");

		if (xl_1.exists()) {
			System.out.println("xl_1 is Exist");
		} else {
			System.out.println("xl_1 doesnot Exist");

		}

		driver.quit();
	}
}