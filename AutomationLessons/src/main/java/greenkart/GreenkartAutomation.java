package greenkart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GreenkartAutomation {


		public static void main(String[] args) throws InterruptedException {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
			//implicitWait
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 
			//ExplicitWait()
			WebDriverWait wait =new WebDriverWait(driver,5);

			driver.get("https://rahulshettyacademy.com/seleniumPractise/");
			Thread.sleep(3000);
			
			String[] vegNeeded = { "Cucumber", "Brocolli", "Beetroot" };// required products in array
	
			productsAddToCart(driver, vegNeeded);        //productsAddToCart(WebDriver driver , String[] vegNeeded) {
			checkOut(driver , wait);        //proceed to checkout method
			
			tearDown(driver);
			
			
		}
		
		public static void checkOut(WebDriver driver , WebDriverWait wait) {
			
			System.out.println("--------checkOut method--------------"); 
			driver.findElement(By.cssSelector("img[alt='Cart']")).click();  //locator for the cartbag
			driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();   //locate the proceedtocheckout button in the Cartbag page
			
			System.out.println("Applying the explicit wait:  for Enter promo code textbox display. ");
			
			//explicit wait
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode"))); //wait for Enter promo code textbox

			driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");//typing rahulshettyacademy- enter promo code
			System.out.println("Entered the promo code: rahulshettyacademy ");
			driver.findElement(By.cssSelector("button.promoBtn")).click(); //click on Apply button for the promo code
       
			//this will take some time to apply the code and to display the confirmation messege - so give wait here
			System.out.println("Applying the explicit wait for the promo code successful confirmation message.  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(.... ");
			
			//explicit wait
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));   //confirmation msg for the promo code
			System.out.println("Promo code confirmation messgae!!  "+driver.findElement(By.cssSelector("span.promoInfo")).getText());  //getting the confirmation msg for the promo code

		}
		
		public static void productsAddToCart(WebDriver driver , String[] vegNeeded) {
		System.out.println("------------productsAddToCart method-------------"); 
		System.out.println("The no of required products in the array is " + vegNeeded.length);   //size of the array vegNeeded[]
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name")); //locating the prodcuts in the application--all products --30--list for located products
		  // products.size();                                     //>> gives the size/count of products in the list
		int j = 0;                                            // counter to break the for loop once the products in the array are added to cart use a counter
		for (int i = 0; i < products.size(); i++)                 // iterate through the list and check the name of the product
		{        //Brocolli - 1 Kg
			String productName = products.get(i).getText();                       // name of product at index i		// 11.format it to get actual vegetable name
			String[] productNameSplit = productName.split("-");                       // returns an array with right value and left value name = Cucumber - 1 Kg name1[0] = Cucumber name1[1] = 1 Kg
            String vegName = productNameSplit[0].trim();                              // to trim the white space on bothsides
            List vegNeededList = Arrays.asList(vegNeeded);                                  //arrayList for the products in the array
			if (vegNeededList.contains(vegName))                             // compare the name -- to check for the required product
			{			
				j++;
				System.out.println("No of the products added to cart is   " +j);
			   driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();  //ADD TO CART locator
				System.out.println("Product:-" + j + " in the array is added to the Cart");
			   if(j == vegNeeded.length)                  //size of the array - no of required products in the array
				{
				System.out.println("vegNeeded[] ArraySize " +vegNeeded.length + "  is Over!!. All products in the array are added to the Cart!!");
				break; //break from the for loop
				}
				System.out.println(j + " Counter");
			}					
		} // for
		}//ProductsAddToCart() {
		
		
			public static void tearDown(WebDriver driver) {
				
				driver.quit();
				System.out.println("-------tearDown!! Done!!!");
			}  //tearDown(WebDriver driver) {
	}

