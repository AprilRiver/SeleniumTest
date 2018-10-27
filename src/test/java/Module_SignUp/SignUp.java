package Module_SignUp;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import xeroAppTesting.ReusableFlows;
import xeroAppTesting.ReusableMethods;
import xeroAppTesting.SetUps;
public class SignUp extends ReusableFlows
{
	@BeforeSuite
	public static void beforeSuite()
	{
		initializeExtentReport();
		readORFile("./src/test/java/repository/objRep.properties");
		readConfigFile("./src/test/java/repository/config.properties");
	}
	@AfterMethod
	public static void afterEachMethod()
	{
		extent.flush();
	}
	
	@Test(enabled = false)
	public static void TC05_GetStarted() throws Exception
	{

		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC05");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC05_data.properties");

		//open browser
		openBrowser("chrome");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//verify sign up is displayed	
		checkTextAnywhereOnPage(dataProp.getProperty("trialPageMessage"), "Sign Up page");

		//click Free trail
		WebElement freeTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freeTrailButton, "Free Trail Button");

		//enter  first name
		WebElement firstName = driver.findElement(getLocator("SignUp.RegisterationPage.firstName"));
		sendText(firstName, "first Name", dataProp.getProperty("firstName"));

		//enter  last Name 
		WebElement lastName = driver.findElement(getLocator("Signup.RegisterationPage.lastName"));
		sendText(lastName, "last Name", dataProp.getProperty("lastName"));

		//enter  emailAddress
		WebElement emailAddress = driver.findElement(getLocator("Signup.RegisterationPage.emailAddress"));
		sendText(emailAddress, "emailAddress ", dataProp.getProperty("emailAddress"));

		//enter  phoneNumber 
		WebElement phoneNumber = driver.findElement(getLocator("Signup.RegisterationPage.phoneNumber"));
		sendText(phoneNumber, "phoneNumber", dataProp.getProperty("phoneNumber"));


		// fix
		//select US as country
		WebElement country = driver.findElement(getLocator("Signup.RegisterationPage.Country_Menu"));	
		country.click();

		WebElement US = driver.findElement(getLocator("Signup.RegisterationPage.Country_Value"));
		US.click();

		//		// click im not a robot
		//		WebElement reCaptcha = driver.findElement(By.className("recaptcha-checkbox-checkmark"));	
		//		reCaptcha.click();

		//click agree check box
		WebElement termsAgree = driver.findElement(getLocator("Signup.RegisterationPage.Terms_Agree_checkBox"));	
		clickElement(termsAgree, "Agree to terms ");

		//click get started
		WebElement getStarted = driver.findElement(getLocator("Signup.RegisterationPage.GetStarted_button"));	
		clickElement(getStarted, "Get Started button");

		//verify confirmation page		
		checkTextAnywhereOnPage("an email is on its way" , "an email is on its way\"");

		closeBrowser();

	}

	@Test(enabled = false)
	public static void TC06_ErrorMessages() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC06");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC06_data.properties");

		//open browser
		openBrowser("firefox");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//click free trail button
		WebElement freeTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freeTrailButton, "Free Trail Button");

		//click get started without entering anything
		WebElement getStarted = driver.findElement(getLocator("Signup.RegisterationPage.GetStarted_button"));	
		clickElement(getStarted, "Get Started button");

		//verify error messages
		checkTextAnywhereOnPage(dataProp.getProperty("firstNameErrorMessage"), "firstNameErrorMessage");
		checkTextAnywhereOnPage(dataProp.getProperty("lastNameErrorMessage"), "lastNameErrorMessage");
		checkTextAnywhereOnPage(dataProp.getProperty("emailErrorMessage"), "emailErrorMessage");
		checkTextAnywhereOnPage(dataProp.getProperty("phoneNumberErrorMessage"), "phoneNumberErrorMessage");

		closeBrowser();
	}

	@Test
	public static void TC07_verifyPrivacyPolicyLink() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC07");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC06_data.properties");

		//open browser
		openBrowser("firefox");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//click free trail
		WebElement freTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freTrailButton, "free Trail Button");

		//store current window before clicking the link
		String winHandleBefore = driver.getWindowHandle();

		//click terms of use link
		WebElement termsOfUseLink = driver.findElement(getLocator("SignUp.termsOfUse_link"));
		clickElement(termsOfUseLink, "terms of use");

		Thread.sleep(3000);

		// go to new tab

		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		System.out.println("switched to new tab \n");
		Thread.sleep(3000);


		//click privacy policy link
		WebElement privacyPolicyLink = driver.findElement(getLocator("SignUp.privacyPolicyLink"));
		clickElement(privacyPolicyLink, "Privacy policy link");

		System.out.println("clicked privacy policy \n");

		//verify - A new Privacy policy web page should open
		checkTextAnywhereOnPage("Privacy notice" , "privacy policy page");	
		logger.log(Status.PASS, MarkupHelper.createLabel("A new Privacy policy web page  opened", ExtentColor.GREEN));

		System.out.println(" A new Privacy policy web page  opened");

		// Close the new tab
		driver.close();

		// Switch  to original tab
		driver.switchTo().window(winHandleBefore);

		closeBrowser();
	}

	@Test

	public static void TC08_newOffersLink() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC08");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC05_data.properties");

		//open browser
		openBrowser("firefox");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//click free trail
		WebElement freTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freTrailButton, "free Trail Button");

		//store current window before clicking the link
		String winHandleBefore = driver.getWindowHandle();

		//click offer details link
		WebElement offerDetailsLink = driver.findElement(getLocator("SignUp.offerDeatilsLink"));
		clickElement(offerDetailsLink, "offer details");

		// go to new tab
		Thread.sleep(3000);
		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		System.out.println("switched to new tab \n");
		Thread.sleep(3000);

		//verify Offer details page is disaplyed
		checkTextAnywhereOnPage("Offer details " , "Offer details  page");	

		// Close the new tab
		driver.close();

		// Switch  to original tab
		driver.switchTo().window(winHandleBefore);

		closeBrowser();
	}

	@Test
	public static void TC09_accountantBookkeeperLink() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC09");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC05_data.properties");

		//open browser
		openBrowser("firefox");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		//click free trail
		WebElement freTrailButton = driver.findElement(getLocator("SignUp.freeTrailButton"));
		clickElement(freTrailButton, "free Trail Button");

		//store current window before clicking the link
		String winHandleBefore = driver.getWindowHandle();


		//click accountant or book keeper  link
		WebElement accountantBookkeeperLink = driver.findElement(getLocator("SignUp.accountantBookkeeperLink"));
		clickElement(accountantBookkeeperLink, "accountant or Bookkeeper Link");

		Thread.sleep(3000);
		// go to new tab

		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		System.out.println("switched to new tab \n");
		Thread.sleep(3000);

		//verify - A new Let's start a great partnership web page open
		checkTextAnywhereOnPage("Let’s get started" , " A new Let's start a great partnership web page");	

		closeBrowser();

		// Close the new tab
		driver.close();

		// Switch  to original tab
		driver.switchTo().window(winHandleBefore);

		closeBrowser();
	}

}
