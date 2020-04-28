package learningTestCase;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class TC7_Honda {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("Webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
	    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		
		String url = "https://www.honda2wheelersindia.com/";

		// 1) Go to https://www.honda2wheelersindia.com/
		driver.get(url);

		driver.findElementByXPath("//button[@class=\"close\"]").click();
		// 2) Click on scooters and click dio
		driver.findElementByLinkText("Scooter").click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//img[@src=\"/assets/images/thumb/dioBS6-icon.png\"]")).click();
		Thread.sleep(1000);
		String dioTitle = driver.getTitle();
		System.out.println(dioTitle);

		// 3) Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		Actions build = new Actions(driver);
		Thread.sleep(3000);
		WebElement dioEngine = driver.findElementByLinkText("ENGINE");
		build.moveToElement(dioEngine).perform();

		// 4) Get Displacement value
		// String dioDpValue =
		// driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText().replaceAll("[A-Za-z]",""
		// );
		// double diodisp = Double.parseDouble(dioDpValue);
		String dis = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText()
				.replaceAll("//D", "").substring(0, 6);
		System.out.println("Displacement Value:" + dis);
		double disValue = Double.parseDouble(dis);

		// 5) Go to Scooters and click Activa 125
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		String actTitle = driver.getTitle();
		System.out.println(actTitle);
		Thread.sleep(3000);

		// 6) Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		WebElement actEngine = driver.findElementByLinkText("ENGINE");
		build.moveToElement(actEngine).perform();
		Thread.sleep(2000);

		// 7) Get Displacement value
		String dis1 = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText()
				.replaceAll("//D", "").substring(0, 3);
		System.out.println("Displacement Value:" + dis1);
		double disValue1 = Double.parseDouble(dis1);

		// parseDouble returns a primitive double containing the value of the string:
		// Returns a new double initialized to the value represented by the specified
		// String, as performed by the valueOf method of class Double.

		// 8) Compare Displacement of Dio and Activa 125 and print the Scooter name
		// having better Displacement.
		if (disValue > disValue1) {
			System.out.println("Dio has better Displacement");
		} else {
			System.out.println("Activa has better Displacement");
		}

		// 9) Click FAQ from Menu
		driver.findElementByLinkText("FAQ").click();

		// 10) Click Activa 125 BS-VI under Browse By Product
		driver.findElementByLinkText("Activa 125 BS-VI").click();

		// 11) Click Vehicle Price
		driver.findElementByXPath("//a[@data-toggle=\"tab\"]//following :: li[5]").click();

		// 12) Make sure Activa 125 BS-VI selected and click submit
		WebElement ele = driver.findElementById("ModelID6");
		Select model = new Select(ele);
		List<WebElement> allmod = model.getAllSelectedOptions();
		for (int i = 0; i < allmod.size(); i++) {
			if (allmod.get(i).getText().contains("Activa 125")) {
				System.out.println("Activa 125 BS-VI is selected ");
			}
		}
		driver.findElementById("submit6").click();
		// 13) click the price link
		driver.findElementByLinkText("Click here to know the price of Activa 125 BS-VI.").click();
		// 14) Go to the new Window and select the state as Tamil Nadu and city as
		// Chennai
		Set<String> winHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winHandles);
		driver.switchTo().window(winList.get(1));
		System.out.println(driver.getTitle());
		WebElement stateID = driver.findElementById("StateID");
		Select state = new Select(stateID);
		state.selectByValue("28");

		WebElement cityID = driver.findElementById("CityID");
		Select city = new Select(cityID);
		city.selectByValue("1524");

		// 15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();

		// 16) Print all the 3 models and their prices
		List<WebElement> list = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr");
		int size = list.size();
		List<WebElement> model1 = driver
				.findElementsByXPath("//table[@id='gvshow']//tbody//tr//td[contains(text(),'ACTIVA')]");
		List<WebElement> price = driver
				.findElementsByXPath("//table[@id='gvshow']//tbody//tr//td[contains(text(),'Rs')]");
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < size; i++) {
			String nameOfModel = model1.get(i).getText();
			String priceOfModel = price.get(i).getText();
			map.put(nameOfModel, priceOfModel);
		}
		System.out.println("all the 3 models and their respective prices");
		for (Entry<String, String> eachEntry : map.entrySet()) {
			System.out.println(eachEntry.getKey() + "-->" + eachEntry.getValue());
		}

		/*
		 * List<WebElement> rows =
		 * driver.findElementsByXPath("//tbody[@style=\"background-color:white\"]//tr");
		 * //List of row
		 * 
		 * for(int i=0; i < rows.size(); i++) { WebElement eachRow = rows.get(i);// To
		 * get each row
		 * 
		 * List <WebElement> cols =driver.findElementsByXPath(
		 * "//tbody[@style=\"background-color:white\"]//tr[\"+(i+1)+\"]//td"); //List of
		 * coloumn if(cols.size()==3) { for(int j =0; j < cols.size(); j++) {
		 * System.out.print(driver.findElementByXPath(
		 * "//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+2)+"]").
		 * getText()); System.out.print(" "); } } else { for(int j=0 ; j <
		 * cols.size();j++) { System.out.print(driver.findElementByXPath(
		 * "//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+1)+"]").
		 * getText()); System.out.print(" "); } }
		 */

		// 17) Close the Browser
		driver.quit();

	}

}
