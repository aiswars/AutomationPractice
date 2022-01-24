package greenkart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GreenkartPractice {

	public static void main(String[] args) throws InterruptedException {

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
									
				String[] vegNeeded = { "Cucumber", "Brocolli", "Beetroot" ,"Pumpkin", "Banana"};// required products in array
				driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		      	Thread.sleep(3000);
		        
		      	int j = 0; // to break the for loop once the products in the array are added to cart use a counter
				
				// 1 // List of elements having this css // Locate the elements 
				List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name")); //list for located products
				// 2 products.size(); >> gives the size/count of products in the list
				for (int i = 0; i < products.size(); i++) // iterate through the list and check the name of the product
				{ // 3
					String productName = products.get(i).getText();// name of product at index i		// 11.format it to get actual vegetable name
					String[] productNameSplit = productName.split("-");// returns an array with right value and left value name = Cucumber - 1 Kg name1[0] = Cucumber name1[1] = 1 Kg
		            String vegName = productNameSplit[0].trim(); // to trim the white space on bothsides
		        
		            //arrayList for the products in the array
		            List vegNeededList = Arrays.asList(vegNeeded);		// 8. convert array to arraylist - array takes less memory - at run time// coverting to arraylist --google the conversion
					
		          	if (vegNeededList.contains(vegName)) // compare the name -- to check for the required product
					{
						j++;
						System.out.println("The products added to cart count is " +j);
					   driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();  //ADD TO CART locator
						if(j == vegNeeded.length)//size of the array - no of required products in the array
						{
						System.out.println("ArraySize " +vegNeeded.length + "is Over!!");
						break; 
						}
						System.out.println("Product: " + j + " in the array is added to the Cart");
   					}
					

					// 4. Cucumber - 1 Kg compare the name --use ignoreCase or contains --As name is with 1kg its better not to use ignoreCase
					/*
					 * if (name.contains("Cucumber")) // compare the name -- to check for the required product
						{
					 * //5. click on Add to Cart 
					 * // 5
					 * driver.findElements(By.xpath("//button[text() = 'ADD TO CART']")).get(i).click(); // xpath by text() -  but the text changes>> 15 // change once it ic //
					  * // 6 break;     // once the required product is added --no need to check for the remaining // prodcuts -- break the for loop 
					 * // * driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
					 * * } // if(name.contains("Cucumber")) {
					 */
					
					
				} // for
			}

		
}

//1. To Locate the elements to add use findelements-- List of elements having the css -- a list
	//2. products.size(); >> gives the size/count of products in the list
	//3. getting name of product at index i
	//4. Cucumber - 1 Kg - 3& 4 --type the name and compare the name --use ignoreCase or contains --As name is with 1kg its better not to use ignoreCase
		 // compare the name -- to check for the required product 			 * 
	//5. click on Add to Cart 
	//6.  break; // once the required product is added --no need to check for the remaining // prodcuts -- break the for loop 
	//7. if more no of products to add or to add more prodcuts any time -- to make the program generic use array to store the products-- keep on adding products
			// here as needed-- no change required to the code --just add the product names here
	//8. 4 to 8 convert array to arraylist - array takes less memory so storing the required products in an Array  -
	     //and at run time coverting to arraylist as its easier to get the products--google the conversion
	//9. //4 to 9- check whether name you extracted is present in arrayList or not// Arraylist also has contains method
	//10. 5 to 10 //click on Add to cart
	//11. Cucumber - 1 Kg -the product name in application is like these- so add // like these in the products array or split it
	         //split returns 2 values in array -- returns an array with 2 values -- with right value and left value
				// name = Cucumber - 1 Kg		// name1[0] = Cucumber	// name1[1] = 1 Kg
	//12. to trim the white space on bothsides
	//13. //9 to 13 compare the name -- to check for the required product
	//14. //6 to 14 -- once the required product is added --no need to check for the remaining prodcuts -- to break the for loop use a counter
	      //size of the array - no of required products in the array{break; 
	      //once the required product is added - to break the for loop use a counter
	//15.	10 to 15// xpath by text() -// but the text	// change once it is // clicked-- ADD TO CART to ADDED TO CART--SO ITS	// Better not to use text() -// locator text
	      //ADD TO CART index is changed here when driver coming to locate the next product
	//16. 	Thread.sleep(3000);

	
