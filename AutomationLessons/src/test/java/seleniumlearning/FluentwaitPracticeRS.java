package seleniumlearning;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentwaitPracticeRS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver","./Drivers//chromedriver.exe");
		   WebDriver driver =new ChromeDriver();
		   driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		   driver.findElement(By.xpath("//div[@id='start']/button")).click();
			// Waiting 30 seconds for an element to be present on the page, checking
			// for its presence once every 3 seconds.
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)) // 5, SECONDS deprecated
					.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class); // openqa.selenium.NoSuchElementException

			WebElement foo = wait.until(new Function<WebDriver, WebElement>() // import java.util.function.Function
			{
				public WebElement apply(WebDriver driver) // apply method to customize as our requirement
				{
					// customization for this webelement -- if - else for
					// //visibilityofEleemntLocated -- the elemtn is there on the page (html),
					// but its not visible - its loading - to make sure it isin visible mode --
					// visibilityofEleemntLocated method of WedDriverwait -explicit wait
					if (driver.findElement(By.cssSelector("div[id='finish'] h4")).isDisplayed()) // visibilityofEleemntLocated
					{
						return driver.findElement(By.cssSelector("div[id='finish'] h4")); // Hello World! text
					} else {
						return null;
					}
				}
			} ); // ); WebElement foo = wait.until(new Function

			System.out.println(driver.findElement(By.cssSelector("div[id='finish'] h4")).isDisplayed());// no above if else will give false here
			System.out.println(driver.findElement(By.cssSelector("div[id='finish'] h4")).getText());
			
			
		   
			/*  Fluentwait syntax
			 * -----------------------------
			 * // Waiting 30 seconds for an element to be present on the page, checking //or its presence once every 3 seconds.  
			 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) 
			 *        .withTimeout(Duration.ofSeconds(30))
			 *        .pollingEvery(Duration.ofSeconds(3)) 
			 *        .ignoring(NoSuchElementException.class); //5, SECONDS deprecated
			 * 
			 * 
			 * WebElement foo = wait.until(new Function<WebDriver, WebElement>() //import java.util.function.Function
			 * { 
			 * public WebElement apply(WebDriver driver) //apply method to customize as our requirement 
			 * { 
			 *     // return driver.findElement(By.id("foo"));
			 *   return driver.findElement(By.cssSelector("[id='finish']h4")); // java.util.function.Function; 
			 *     //visibilityofEleemntLocated 
			 *  }
			 * } );   // ); WebElement foo = wait.until(new Function
			 */
		   
		   
		} //public static void main(String[] args) {
		

}
