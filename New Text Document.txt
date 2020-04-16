import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

	public class TC1_Myntra {
	
		public static void main(String[] args) throws InterruptedException {
			 
			   System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			   ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				ChromeDriver driver = new ChromeDriver(options);
				
				//1) Open https://www.myntra.com/
			   driver.get("https://www.myntra.com/");
			   driver.manage().window().maximize();
			   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			   
			   //2) Mouse over on WOMEN (Actions -> moveToElement
			   WebElement move = driver.findElementByXPath("//a[text()='Women']");
			   Actions builder = new Actions(driver);//Y need to pass the driver obj here *
			   builder.moveToElement(move).perform();
			   
			   //3) Click Jackets & Coats
			   driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
			   
			   //4) Find the total count of item (top) -> getText -> String	 
			   String count = driver.findElementByXPath("//span[@class='title-count']").getText();
			   System.out.println("Total Count of Item:"+count);
			   count = count.replaceAll("\\D","");// -> String
			    int totalCount= Integer.parseInt(count); //-> int
	            
				//5) Validate the sum of categories count matches
			    String jacketsCount = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
			    System.out.println("Total Count of Jacket:"+jacketsCount);
			    String coatsCount = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
			    System.out.println("Total Count of Coats:"+coatsCount);
			    jacketsCount = jacketsCount.replaceAll("\\D", "");
			    coatsCount = coatsCount.replaceAll("\\D", "");
			    int jCount = Integer.parseInt(jacketsCount);
			    int cCount = Integer.parseInt(coatsCount);
			    int totalJacketCoats = jCount + cCount;
			    
			    System.out.println("Jacket Counts + Coat Counts:"+totalJacketCoats);
			    if(totalCount==totalJacketCoats)
			    	    System.out.println("The Sum of Jackets and Coats Matches with the total");
			    else 
			    	System.out.println("The Sum of Count is Not Matched");
				// 6)Check Coats
			    driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
			    // 7)Click + More option under BRAND
			    driver.findElementByXPath("//div[@class='brand-more']").click();
				// 8)Type MANGO and click checkbox
		        driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("Mango");
		        driver.findElementByXPath("//label[@class=' common-customCheckbox']//div").click();
				//9) Close the pop-up x
		        driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']/span[1]").click();
		        Thread.sleep(5000);
	            //10) Confirm all the Coats are of brand MANGO
		        List<WebElement> brandList = driver.findElementsByXPath("//h3[@class='product-brand']");
		        
		          for (WebElement eachBrand : brandList) {
		          String brand = eachBrand.getText();
		          System.out.println("Brand Name:"+brand);
		          }
		
			    // 11)Sort by Better Discount
		    	builder.moveToElement(driver.findElementByClassName("sort-sortBy")).perform();
		    	driver.findElementByXPath("//label[text()='Better Discount']").click();
		    	Thread.sleep(3000);
		          
			    //12)Find the price of first displayed item
		    	List<WebElement> priceList = driver.findElementsByClassName("product-discountedPrice");
		        String price = priceList.get(0).getText();
		        System.out.println("The Price of first item is:"+price);
		        
				// 13)Mouse over on size of the first item
		        WebElement size = driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]");
		        builder.moveToElement(size).perform();
				// 14)Click on WishList Now
		        driver.findElementByXPath("//span[text()='wishlist now']").click();
				//15)Close Browser
	            driver.close();
		}
	
	}
