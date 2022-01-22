
		import java.util.Iterator;
		import java.util.Set;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.Keys;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.Select;
		import org.openqa.selenium.support.ui.WebDriverWait;

		import io.github.bonigarcia.wdm.WebDriverManager;
		
public class HandlingWebelements2 {
	
	public static WebDriver driver;
	
	public static void main(String[] args) {
		setup();
		//checkLandingPage();
		// handlingWebelements();
		//handlingRadioButtons();
		//handlingSuggestionList();
		//handlingDropdown();
		//handlingCheckbox();
		//handlingWindowHandles();
		//switchTabs();
		//handlingAlerts();
		//isElementDisplayed();
		//mouseHoverActions();
		//handlingWebtables();
	//  Practice frmaes webtables	frames
		//
		tearDown();

	}// psvm
	
	

	public static void setup() // driver initialization
	{
		System.out.println("setup!! // driver initialization");
		System.out.println("-----------------------------------------------------------------------");
	   
		WebDriverManager.chromedriver().setup(); // no driver.exe path - WebDriverManager API dependency in pom.xml
		// System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

	}
	
	public static void handlingFrames() // frames
	{
		System.out.println();
	System.out.println("handlingFrames!!");
	System.out.println("-----------------------------------------------------------------------");
	driver.findElement(By.xpath("//a[@href='/nested_frames']")).click();
	
	//give just the name of frame instead of giving driver.find.....
	driver.switchTo().frame("frame-top"); 
	//	driver.switchTo().frame(driver.findElement(By.name("frame-top")));
	
	
	System.out.println("switched to frame top");
	driver.switchTo().frame("frame-top");
	System.out.println(driver.findElements(By.tagName("frame")).size());// count of frames in frame top
	driver.switchTo().frame(1);
	System.out.println(driver.findElement(By.id("content")).getText());
	
	driver.switchTo().defaultContent();
	}//handlingFrames() // frames
	
	
	//------------WEBTABLES---------------------------
	//]---------------------------------------------------
	public static void handlingWebtables() {
		System.out.println();
		System.out.println("handlingWebtables!!");
		System.out.println("-----------------------------------------------------------------------");
		
	//  table1.findElements(By.xpath(" ...............   limited the scope only on this webelement - not the entire page
		
		//WebTable Example
		WebElement table1 = driver.findElement(By.id("product"));  //legend[normalize-space()='Web Table Example']
		//  table1.findElements(By.xpath(" ...............   limited the scope only on this webelement - not the entire page
		int columnsWebtable1 = table1.findElements(By.xpath("//table[@id='product']/child::*")).size(); //no of columns in table1- Web Table Example
		if (columnsWebtable1 == 3) {
			System.out.println("columnsWebtable=3 PASSED >> The columns count in Webtable1 Example is  " + columnsWebtable1);
		}
		else {
			System.out.println("columnsWebtable !=3 FAILED>> The columns count in Webtable1 Example is  " + columnsWebtable1);
		}
			
		int rowsWebtable1 = table1.findElements(By.tagName("tr")).size(); // count of rows in table1
		if (rowsWebtable1 == 11) { //  11 including header
			System.out.println("rowsWebtable1=11 PASSED >> The rows count in Webtable1 Example is  " + rowsWebtable1);
		}
		else {
			System.out.println("rowsWebtable1 !=11 FAILED>> The rows count in Webtable1 Example is  " + rowsWebtable1);
		} 
		
		//int columns2 = table1.findElements(By.xpath("//tr//th")).size();
		//System.out.println("The columns count is " + columns2);
		int table1RowThree = table1.findElements(By.xpath("//tr[3]//td")).size();  //no of cells-columnsno in 3rd row -- only 3 but getting 7-  wrong
		if (table1RowThree == 3) { //  11 including header
			System.out.println("table1RowThree=3 PASSED >> The column/cells in row three of Webtable1 Example is  " + table1RowThree);
		}
		else {
			System.out.println("table1RowThree !=3 FAILED>> The column/cells in row three of Webtable1 Example is  " + table1RowThree);
		} 
		
		for (int i = 0; i < table1RowThree; i++) {//reading the values in rowThree

			System.out.print(   table1.findElements(By.xpath("//tr[3]//td")).get(i).getText()   + "    " ); //cell values with space

		}
	}//handlingWebtables() {
	
	
	/*
	 * public static void test() {
	 * 
	 * Count the Entire links of the Footer Link only.. //This is called Limiting
	 * the driver... WebElement FooterDriver =
	 * driver.findElement(By.xpath("//div[@id='gf-BIG']"));
	 * System.out.println("Number of footer links: "
	 * +FooterDriver.findElements(By.tagName("a")).size());
	 * 
	 * //Count the 1st section link of the footer driver... WebElement Section1 =
	 * FooterDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
	 * System.out.println("Number of footer Section 1 links: "
	 * +Section1.findElements(By.tagName("a")).size());
	 * 
	 * //Get the text named discount coupen..
	 * System.out.println(driver.findElement(By.
	 * xpath("//*[text()='Discount Coupons']")).getText());
	 * 
	 * //Click on each footer section 1 links and open it in the another tab..
	 * //Using Forloops.. for(int i=1 ;
	 * i<Section1.findElements(By.tagName("a")).size(); i++) { //Press control keyt
	 * so that it will get open into the newtab.. String Clickoneachlinks =
	 * Keys.chord(Keys.CONTROL,Keys.ENTER);
	 * 
	 * //Getting i so that it is common for every links... //For every Keyboard
	 * events we have to use sendkeys method..
	 * Section1.findElements(By.tagName("a")).get(i).sendKeys(Clickoneachlinks);
	 * Thread.sleep(4000); }
	 * 
	 * Set<String> abc=driver.getWindowHandles();//4 Iterator<String>
	 * its=abc.iterator();
	 * 
	 * while(its.hasNext()) //it.hasnext() it will search if there are any index
	 * present or not... {
	 * 
	 * driver.switchTo().window(its.next()); //it.next() will move to another tab...
	 * System.out.println(driver.getTitle());
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	//MOUSE ACTIONS--------------
	//-----------------------------------------------------
	public static void mouseHoverActions() { 
		System.out.println();
		System.out.println("mouseHoverActions!!");
		System.out.println("-----------------------------------------------------------------------");
	
	//Mouseover Actions Example --MOuse Hover Button
			WebElement btnMouseHover = driver.findElement(By.xpath("//button[@id='mousehover']")); //locating the Mouse Hover BUtton
			Actions act = new Actions(driver); // objectname act - Actions Class object
			act.moveToElement(btnMouseHover).build().perform(); //move to the element Mouse Hover Button - Reload and Top button will be available then
			System.out.println("Mouse Hover button is accessed!!");
			
			//to clcik/Move to Reload button   ---//Reload the page using mouseover
			WebElement mouseHoverReload = driver.findElement(By.xpath("//div[@class='mouse-hover-content']/a[contains(text(),'Reload')]"));		//locating the Reload button
			act.click(mouseHoverReload).build().perform(); //clcik the Relaod button
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Assert to verify the page is loaded
			System.out.println("MouseAction: ReLoad Button is clicked!!");
			
			//to click on Top button
			//DIDNT WOTK THIS PART ?? >> stale element reference: element is not attached to the page document 
			//act.moveToElement(btnMouseHover).build().perform();
			act.moveToElement(btnMouseHover).build().perform(); //to move to the Mouse Hover BUtton again to clcik the Top button -Reload and Top button will be available then
			WebElement mouseHoverTop = driver.findElement(By.xpath("(//a[normalize-space()='Top'])[1]")); //locating the Top Button
			act.click(mouseHoverTop).build().perform();
			//Assert to verify the cursor/focus is on the TOP of the page 
			System.out.println("MouseAction: Top Button is clicked!!");
			//xpath------------------------------------------
			//Reload the page using mouseover... parentxpath/childtag[contains[text()  
			//div[@class='mouse-hover-content']/a[contains(.,'Reload')]
			//div[@class='mouse-hover-content']/a[contains(text(),'Reload')]
			//*[@class='mouse-hover-content']/a[2] - index xpath
			
	}//mouseHoverActions() { 
	
	//ELEMENT DISPLAYED or NOT
	//-----------------------------------------------------------------------------
	public static void isElementDisplayed() { 
		System.out.println();
		System.out.println("isElementDisplayed!!");
		System.out.println("-----------------------------------------------------------------------");
	
		//Element Displayed Example
		Boolean isInputboxDisplayed = driver.findElement(By.id("displayed-text")).isDisplayed();
		if ( isInputboxDisplayed  == true) {
			System.out.println("The textbox for the input is displayed on the page!! txtInputbox:" +isInputboxDisplayed);
			//Assert to check whether the textbox is dsiplayed by default on page load
			driver.findElement(By.id("hide-textbox")).click(); ////click on Hide Button to hide the text box
		   }
		else {
			System.out.println("The textbox for the input is not displayed on the page!! txtInputbox: " +isInputboxDisplayed);
		}
		    //driver.findElement(By.id("hide-textbox")).click(); ////click on Hide Button to hide the text box
			driver.findElement(By.id("show-textbox")).click(); //click on the show button to show the textbox
			driver.findElement(By.id("displayed-text")).sendKeys("The textbox is displayed by default");
			driver.findElement(By.id("hide-textbox")).click(); ////click on Hide Button to hide the text box
			driver.findElement(By.id("show-textbox")).click(); //click on the show button to show the textbox
}
	
	// RADIOBUTTONS - CLICK()
	//-----------------------------------------------------------------------------------------------
		public static void handlingRadioButtons() {
			System.out.println();
			System.out.println("handlingRadioButtons!!");
			System.out.println("-----------------------------------------------------------------------");
		
			//public static void handlingWebelements() {
			// WebDriverWait mywait=new WebDriverWait(driver,10000);//check Explicit wait??
			
		driver.findElement(By.xpath("//input[@value='radio1']")).click();
		driver.findElement(By.xpath("//input[@value='radio3']")).click();
		System.out.println(driver.findElement(By.xpath("//*[@type='radio']")));
		//int countRadiobutton = driver.findElement(By.xpath("//*[@type='radio']")).getSize();
		//System.out.println("No of Radiobuttons: " + ; //Number of radio buttons..
		
		}  //handlingRadioButtons() {
		
		
		// SUGGESTION BOX/LIST - TEXTBOX - SENDKEYS(), .CLICK(), .CLEAR(), .KEYS.DOWN, .KEYS.ENTER
		//----------------------------------------------------------------------------------------------------
		public static void handlingSuggestionList() {
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
		suggestionCountryName.sendKeys("Ja"); // typing Ja -- shows 5 countrynames
		System.out.println(suggestionCountryName.getText()); // printing the country name
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

			System.out.println("Selected country - " + suggestionCountryName.getText()); // // printing the country name >> didnt print the names???
		}
		suggestionCountryName.sendKeys(Keys.ENTER);// selecting 4th country name
		suggestionCountryName.getText();
		System.out.println("Selected country - " + suggestionCountryName.getText());

		// WebDriverWait mywait=new WebDriverWait(driver,10000);
		// mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userName")));
		
		
		} //handlingSuggestionList() {
		

		
		// STATIC DROPDOWN -SELCT TAG IN DOM - USE SELE SELECT CLASS -
		//--------------------------------------------------------------------------		
		public static void handlingDropdown() { //STATIC DROPDOWN -SELCT 
			System.out.println();
		System.out.println("handlingDropdown!!");
		System.out.println("-----------------------------------------------------------------------");
		WebElement dropDown = driver.findElement(By.xpath("//select[@id='dropdown-class-example']")); // locating the// dropdown box on the page
		dropDown.click(); // clicking on the drip down box //without clicking the options can be accessed ??dropdownOptions.selectByVisibleText("Option2"); >> check assignment6 -  
		Select dropdownOptions = new Select(dropDown); //Select class in sele
		dropdownOptions.selectByVisibleText("Option2"); // <option value="option2" xpath="1">Option2</option>
		// dropdownOptions.selectByIndex(2); //index ??
		// dropdownOptions.selectByValue("option1"); //value="option1" in DOM >> <option
		// value="option1" xpath="1">Option1</option>
		String optionName = dropdownOptions.getFirstSelectedOption().getText();//text of selected option
		System.out.println("text of selected dropdown option  " +optionName);

		
	} //handlingDropdown() {        // handlingWebelements() {
		
		
        //CHECKBOX
		//------------------------------------------------------------------------------------------------------
	public static void handlingCheckbox() {
		System.out.println();
		System.out.println("handlingCheckbox!!");
		System.out.println("-----------------------------------------------------------------------");
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
		
	
	//MULTIPLE WINDOWS _ WINDOW HANDLES
	//----------------------------------------------------------------------------------------------
	public static void handlingWindowHandles() { // multiple windows
		System.out.println();
		System.out.println("handlingWindowHandles!!");
		System.out.println("-----------------------------------------------------------------------");
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
		 * Set<String> ids = driver.getWindowHandles(); //java.util.
		 * Iterator<String> itr = ids.iterator(); 
		 *  Iterator<String> itr = ids.iterator(); 
		 *  String parentId = itr.next();
		 *   String childId = itr.next();
		 * driver.switchTo().window(childId);
		 * System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3"))
		 * .getText()); driver.switchTo().window(parentId);
		 * System.out.println(driver.findElement(By.
		 * xpath("//h3[contains(text(),'Opening a new window')]")).getText());
		 * 
		 */
	}// handlingWindowHandles {
	
	//Switch TABS
	//--------------------------
	public static void switchTabs() { // multiple windows handlingWindowHandles()
		System.out.println();
		System.out.println("switchTabs!!");
		System.out.println("-----------------------------------------------------------------------");
		driver.findElement(By.xpath("//a[@id='opentab']")).click(); // Click on Open Tab Button -a new tab will be opened
		//driver.getWindowHandles(); // set of windowHandles
		Set<String> handles = driver.getWindowHandles(); //java.util.Iterator<String
		 Iterator<String> itr = handles.iterator();
		 String parentHandle = itr.next();
		 String childHandle = itr.next();
		 driver.switchTo().window(childHandle);
		 String headerOnChildTab = driver.findElement(By.xpath("//div[@class='container-fluid']//h2//span[1]")).getText();
		 System.out.println("A new tab is opened!! headerOnChildTab is  " + headerOnChildTab);
		 if (headerOnChildTab == "An Academy to Learn Earn & Shine  in your QA Career") {
			 System.out.println("A new tab is opened!!");
		 }
		 driver.switchTo().window(parentHandle);  // parent window handle - to use to move back from the child window										
		driver.getTitle();
		if (driver.getTitle() == "Practice Page") {
			System.out.println("Moved back to parent tab -Practice Page!!");
		}
		
	}
	
	//ALERTS - POPUP WINDOWS
	//----------------------------------------------------------------------------
	public static void handlingAlerts() { //assignment6
		System.out.println();
	System.out.println("handlingAlerts!!");
	System.out.println("-----------------------------------------------------------------------");
	driver.findElement(By.id("checkBoxOption1")).click();  //first checkbox click
	String chkbox1name = driver.findElement(By.xpath("//div[@id='checkbox-example']//label[1]")).getText(); ////first checkbox name
	System.out.println("chkbox1name  " +chkbox1name);
	
	//select the option in the dropdown with the checkbox1 name 
	Select dropDown = new Select(driver.findElement(By.id("dropdown-class-example"))); //accessing dropdown box
	dropDown.selectByVisibleText(chkbox1name); //select the option in the dropdown with the checkbox1 name 
	
	driver.findElement(By.id("name")).sendKeys(chkbox1name); //typing the checkbox1name in the alert section textbox
	//driver.findElement(By.xpath("//input[@id='alertbtn']")).click(); //clicking on alert button 
	
	//---------------------------------------------------------------------------------------
	//handling alert box -- switchTo().alert()   --OK/Accept buttotn on alert popoup --accept()
	//-----------------------------------------------------------------------------------
	driver.findElement(By.xpath("//input[@id='alertbtn']")).click(); //clicking on alert button
	//  driver.switchTo().alert();    //handling alert box -- switchTo().alert()
	
	String msgAlertBox = driver.switchTo().alert().getText();  //get messgae in the alert box
	System.out.println("msgAlertBox  1  " +msgAlertBox);
	if (msgAlertBox.contains(chkbox1name)) {  //checking string2 is present in string1 - is chkbox1name present in msgAlertBox
		System.out.println("The selected Checkbox label is printed in the messagebox");
		driver.switchTo().alert().accept(); //click on OK/accept button in the alert box
	} else {
		System.out.println("The selected Checkbox label is not printed in the messagebox");
	}
    
	//handling alert box --switchTo().alert().dismiss() --Cancel buttotn on alert popoup -dismiss()
    //--------------------------------------------------------------------------------------
   // driver.findElement(By.xpath("//input[@id='name']")).sendKeys("QA"); //typing text on the textbox
	driver.findElement(By.xpath("//input[@id='confirmbtn']")).click(); //clicking on confirm button
	String msg2AlertBox = driver.switchTo().alert().getText(); //getting alert message 
	System.out.println("msg AlertBox 2 - to click on Cancel  " +msg2AlertBox);//printing alert message 
	driver.switchTo().alert().dismiss(); //click on cancel button in the alert box
	
	//handling alert box switchTo().alert().accept()
	driver.findElement(By.xpath("//input[@id='name']")).sendKeys("QA"); //typing text on the textbox
	driver.findElement(By.xpath("//input[@id='confirmbtn']")).click(); //clicking on confirm button
	System.out.println("msg AlertBox 3 - to click on OK  " +driver.switchTo().alert().getText());//printing alert message 
	driver.switchTo().alert().accept();
}
	
	
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////
	public static void checkLandingPage() { 
		System.out.println();
		System.out.println("checkLandingPage!!");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("The title of the landing page is  "+driver.getTitle()); //title on the browser tab
		//logo on left top - click the logo to open Home page
		driver.findElement(By.cssSelector("img.logoClass")).click();  //opening the Home page
		System.out.println("The title of the Home page is  "+driver.getTitle());  //title on the browser tab
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().back(); //to go back to the Practicepage from the Home page
		System.out.println("The title of the landing page is  "+driver.getTitle());  //title on the browser tab
		System.out.println("No of buttons on the practice page is: "  +  driver.findElements(By.tagName("button")).size()); //Totally 6 buttons in the spage
		System.out.println("Header on the Practice page - "+driver.findElement(By.xpath("//h1[text()='Practice Page']")).getText());  	//Header on the Practice page ->> Practice page
		}
	
	//////////////////////////////////////////////////////////////////////////
	public static void tearDown() {
		System.out.println();
	System.out.println();
	System.out.println("tearDown!!  driver.quit();");
	System.out.println("-----------------------------------------------------------------------");
     System.out.println("Done");
     driver.quit();
	}
	

} //HandlingWebelements2 {



