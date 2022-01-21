import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingWebElements {

		public static WebDriver driver;

		public static void main(String[] args) {

			setup();
			// handlingWebelements();
			// handlingCheckbox();
			handlingWindowHandles();

			tearDown();

		}// psvm

		public static void setup() // driver initialization
		{
			WebDriverManager.chromedriver().setup(); // no driver.exe path - WebDriverManager API dependency in pom.xml -- WebDriverManager-chromedriver driver exe from GitHub
			// System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		}

		public static void handlingWebelements() {
			// WebDriverWait mywait=new WebDriverWait(driver,10000);//check Explicit wait??

			// RADIOBUTTONS - CLICK()
			driver.findElement(By.xpath("//input[@value='radio1']")).click();
			driver.findElement(By.xpath("//input[@value='radio3']")).click();

			//////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// SUGGESTION BOX/LIST - TEXTBOX - SENDKEYS(), .CLICK(), .CLEAR(), .KEYS.DOWN,
			// .KEYS.ENTER
			WebElement suggestionCountryName = driver.findElement(By.xpath("//input[@id='autocomplete']"));
			suggestionCountryName.sendKeys("Fra"); // typing Fra
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			suggestionCountryName.sendKeys(Keys.DOWN);
			suggestionCountryName.sendKeys(Keys.ENTER);
			System.out.println("Selected Country first time - France");

			/// to select the 4th country from the suggested list after typed the letters
			suggestionCountryName.clear(); // clear before giving any input
			suggestionCountryName.sendKeys("Ja"); // typing Ja
			System.out.println(suggestionCountryName.getText());
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 1; i < 5; i++) { // to select the 4th country

				suggestionCountryName.sendKeys(Keys.ARROW_DOWN);// countrynames
				// mywait.until(ExpectedConditions.elementToBeSelected(suggestionCountryName));
				// //check Explicit wait??

				System.out.println("Selected country - " + suggestionCountryName.getText()); // didnt print the names???
			}
			suggestionCountryName.sendKeys(Keys.ENTER);// selecting 4th country name
			suggestionCountryName.getText();
			System.out.println("Selected country - " + suggestionCountryName.getText());

			// WebDriverWait mywait=new WebDriverWait(driver,10000);
			// mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userName")));
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// STATIC DROPDOWN -SELCT TAG IN DOM - USE SELE SELECT CLASS -
			WebElement dropDown = driver.findElement(By.xpath("//select[@id='dropdown-class-example']")); // locating the
																											// dropdown box
																											// on the page
			dropDown.click(); // clicking on the drip down box

			Select dropdownOptions = new Select(dropDown);
			dropdownOptions.selectByVisibleText("Option2"); // <option value="option2" xpath="1">Option2</option>

			// dropdownOptions.selectByIndex(2); //index ??
			// dropdownOptions.selectByValue("option1"); //value="option1" in DOM >> <option
			// value="option1" xpath="1">Option1</option>

			String optionName = dropdownOptions.getFirstSelectedOption().getText();
			System.out.println(optionName);

		} // handlingWebelements() {

		public static void handlingCheckbox() {
			System.out.println("Handling Checkbox!!");
			WebElement chkbox1 = driver.findElement(By.xpath("//input[@id='checkBoxOption1']")); // locating the checkbox
			WebElement chkbox2 = driver.findElement(By.xpath("//input[@id='checkBoxOption2']")); // locating the checkbox
			WebElement chkbox3 = driver.findElement(By.xpath("//input[@id='checkBoxOption3']")); // locating the checkbox
			System.out.println("chkbox1.isDisplayed()  " + chkbox1.isDisplayed()); // displayed or not -boolean true/false
			System.out.println("chkbox1.isEnabled()   " + chkbox1.isEnabled()); // enabled state or not -boolean true/false
			System.out.println("chkbox1.isSelected()   " + chkbox1.isSelected()); // selected or not -boolean true/false
			// add assertions here to verify
			chkbox1.click();
			System.out.println(chkbox1.getText());// ???
			System.out.println("chkbox2.isSelected()  " + chkbox2.isSelected());
			if (!chkbox2.isSelected()) {
				chkbox2.click();
			}
			System.out.println("chkbox2.isSelected()  " + chkbox2.isSelected());
			chkbox3.click();
			System.out.println("chkbox3.isSelected()  " + chkbox3.isSelected());
			// chkbox1.clear(); //didnt work??
			chkbox1.click(); // clicked again to uncheck
			System.out.println("chkbox1.isSelected()  " + chkbox1.isSelected()); // giving true - but it is unchecked??
																					// ---variable name was wrong -chkbox1
			System.out.println("Two checkboxes are selected!");

		}// handlingCheckbox {
			//////////////////////////////////////////////////////////////////////////////////////////////////////////

		public static void handlingWindowHandles() { // multiple windows

			driver.getWindowHandles(); // set of windowHandles
			String parentWindowId = driver.getWindowHandle(); // parent window handle - to use to move back from the child
																// window
			WebElement switchWindowsButton = driver.findElement(By.xpath("//button[@id='openwindow']"));
			switchWindowsButton.click();
			for (String handles : driver.getWindowHandles()) {
				driver.switchTo().window(handles);
				System.out.println("the windowhandle of the current window is " + handles);
			}

			// actions on child window
			String childTitle = driver.getTitle();
			System.out.println("childTitle  " + childTitle);
			driver.findElement(By.xpath("//button[normalize-space()='NO THANKS']")); // NO THANKS BUTTON on popupwindow
			// Assert.assertEquals(driver.getTitle(), "Home | Rahul Shetty Academy");
			// back to parent window again
			driver.switchTo().window(parentWindowId);  //parentWindowId - stored earlier
			String parentTitle = driver.getTitle();
			System.out.println("parentTitle  " + parentTitle);
			// ASSERT ...................

			/*
			 * Set<String> ids = driver.getWindowHandles(); //java.util.Iterator<String> itr
			 * = ids.iterator(); Iterator<String> itr = ids.iterator(); String parentId =
			 * itr.next(); String childId = itr.next();
			 * 
			 * driver.switchTo().window(childId);
			 * System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3"))
			 * .getText()); driver.switchTo().window(parentId);
			 * System.out.println(driver.findElement(By.
			 * xpath("//h3[contains(text(),'Opening a new window')]")).getText());
			 * 
			 */
		}// handlingWindowHandles {

		//////////////////////////////////////////////////////////////////////////
		public static void tearDown() {
			// driver.quit();
			System.out.println("DOne");
		
		}//tearDown() {

	}//HandlingWebElements {


